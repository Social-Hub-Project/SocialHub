import style from "./Chat.module.css";
import { useEffect, useState} from 'react';
import {Client, Message} from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import {getUserstate} from '../../auth';

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
        sender: getUserstate.name,
        receiver: '',
        connected: false,
        content: '',
    });

    useEffect(() => {
        console.log(userData);
    }, [userData]);

    let stompClient: Client | null = null;

    const connect = () => {
        const socket = new SockJS('http://localhost:8080/ws');
        stompClient = new Client({
            webSocketFactory: () => socket,
            brokerURL: 'http://localhost:8080/ws',
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
        stompClient?.subscribe('/user/' + userData.sender + '/private', onPrivateMessage);
        //userJoin();
    };

    // const userJoin = () => {
    //     const chatMessage: ChatMessage = {
    //         sender: userData.sender,
    //         receiver: userData.receiver,
    //         content: userData.content
    //     };
    //     stompClient?.publish({
    //         destination: '/app/message',
    //         body: JSON.stringify(chatMessage),
    //     });
    // };

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

    const handleMessage = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setUserData({ ...userData, content: value });
    };

    const sendPrivateValue = () => {
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
    };

    const handleUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { value } = event.target;
        setUserData({ ...userData, sender: value });
    };

    const registerUser = () => {
        connect();
    };


    return (
            <div className={style.chatbox}>
                <div className={style.topbar}>
                    <div className={style.profilephoto}></div>
                    <input
                        type="text"
                        value={userData.receiver}
                        onChange={(e) => handleUsername(e)}
                        placeholder="Enter username"
                    />
                </div>
                <div className={style.maincontainer}>
                    <input
                        type="text"
                        value={userData.content}
                        onChange={(e) => handleMessage(e)}
                        placeholder="Enter message"
                    />
                    <button onClick={connect}>Connect</button>
                    <button onClick={sendPrivateValue}>Send</button>
                </div>
            </div>

    );
}

export default Chat;