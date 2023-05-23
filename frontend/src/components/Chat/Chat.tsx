import style from "./Chat.module.css";
import { Component, useEffect } from 'react';
import WebSocketService from "../WebSocket/WebSocketService";

const Chat = () => {

    useEffect(() => {
        WebSocketService.connect();
        return () => {
            WebSocketService.disconnect();
        };
    }, []);

    const handleMessage = (message: string) => {
        console.log("handleMessage: "+message);
    };

    WebSocketService.subscribeToMessages(handleMessage);

    const sendMessage = () => {
        const message = 'Hello, user!'; // Replace with your message
        WebSocketService.sendMessage(message);
    };


    return (
            <div className={style.chatbox}>
                <div className={style.topbar}>
                    <div className={style.profilephoto}></div>
                </div>
                <div className={style.maincontainer}></div>
            </div>

    );
}

export default Chat;