import style from "./Chat.module.css";
import {useEffect, useRef, useState} from 'react';
import {Client, Message} from '@stomp/stompjs';
import SockJS from 'sockjs-client';
const fetchUrl = `${process.env.REACT_APP_BACKEND_URL}/app/getUser`;

interface ChatMessage {
    sender: string;
    receiver?: string;
    content: string;
}



const Chat = () => {
    const [privateChats, setPrivateChats] = useState<Map<string, ChatMessage[]>>(new Map());
    const [tab,setTab] =useState("JOIN");
    const [userData, setUserData] = useState<{
        sender: string;
        receiver: string;
        connected: boolean;
        content: string;
    }>({
        sender: '',
        receiver: '',
        connected: false,
        content: '',
    });

    const name = useRef<HTMLInputElement>(null);
    const content = useRef<HTMLInputElement>(null);


    useEffect(() => {
        console.log(userData);
    }, [userData]);

    async function getSender() {
        console.log("token = "+sessionStorage.getItem("userToken"));
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + sessionStorage.getItem("userToken"),
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Credentials': 'true'
            },
            body: JSON.stringify({
                token: "Bearer " + sessionStorage.getItem("userToken"),
            })
        };

        try {
            const response = fetch(fetchUrl, requestOptions)
                .then((response) => response.json())
                .then((body) => {

                    console.log(body)
                    setUserData({ ...userData, sender: body.name + " " + body.surname });
                });
        } catch (err) {
            console.log("conn error");
        }
    }

    let stompClient: Client | null = null;

    const connect = () => {
        const socket = new SockJS('http://localhost:8080/app/ws');
        stompClient = new Client({
            webSocketFactory: () => socket,
            brokerURL: 'http://localhost:8080/app/ws',
            connectHeaders: {},
            debug: () => {},
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
        });
        stompClient.onConnect = onConnected;
        stompClient.onStompError = onError;
        stompClient.activate();
    };


    const onConnected = () => {
        setUserData({ ...userData, connected: true });
        stompClient?.subscribe('/app/user/' + userData.sender + '/private', onPrivateMessage);
        //userJoin();
    };

    const onPrivateMessage = (message: Message) => {
        console.log(message);
        const payloadData = JSON.parse(message.body);
        if (privateChats.get(payloadData.sender)) {
            privateChats.get(payloadData.sender)?.push(payloadData);
            setPrivateChats(new Map(privateChats));
        } else {
            const list: ChatMessage[] = [];
            list.push(payloadData);
            privateChats.set(payloadData.sender, list);
            setPrivateChats(new Map(privateChats));
        }
    };

    const onError = (error: any) => {
        console.log(error);
    };

    const handleMessage = () => {
        const value  = content.current?.value;
        if(value!=undefined)
            setUserData({ ...userData, content: value });
    };

    const sendPrivateValue = () => {
        connect();
        handleMessage();
        getSender().then(r => {

            if (stompClient) {
                const chatMessage: ChatMessage = {
                    sender: userData.sender,
                    receiver: userData.receiver,
                    content: userData.content
                };

                if (userData.sender !== tab) {
                    privateChats.get(tab)?.push(chatMessage);
                    setPrivateChats(new Map(privateChats));
                }

                stompClient.publish({
                    destination: '/app/private-message',
                    body: JSON.stringify(chatMessage),
                });
                setUserData({ ...userData, content: '' });
            }
        });
    };

    const handleUsername = () => {
        //use ref
        //inputref.current.value
        const value  = name.current?.value;
        if(value!=undefined)
            setUserData({ ...userData, receiver: value });
    };

    return (
            <div className={style.chatbox}>
                <div className={style.topbar}>
                    <div className={style.profilephoto}></div>
                    <input
                        ref={name}
                        type="text"
                        placeholder="Enter username"
                    />
                </div>
                <div className={style.maincontainer}>
                    <input
                        ref={content}
                        type="text"
                        placeholder="Enter message"
                    />
                    <button onClick={()=>{
                        handleUsername()
                    }}>Connect</button>
                    <button onClick={sendPrivateValue}>Send</button>
                </div>
            </div>

    );
}

export default Chat;