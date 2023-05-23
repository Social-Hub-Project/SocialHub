import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

const WebSocketService = {
    stompClient: null as Stomp.Client | null,
    sessionId: "",

    connect: () => {
        const socket = new SockJS('/app/chatRoom');
        WebSocketService.stompClient = Stomp.over(socket);
        WebSocketService.stompClient.connect({}, () => {
            let url = WebSocketService.stompClient?.ws.url;
            url = url?.replace(
                "ws://localhost:8080/spring-security-mvc-socket/app/chatRoom/",  "") || "";
            url = url?.replace("/websocket", "") || "";
            url = url?.replace(/^[0-9]+\//, "") || "";
            console.log("Your current session is: " + url);
            WebSocketService.sessionId = url;
        });
    },

    disconnect: () => {
        if (WebSocketService.stompClient) {
            WebSocketService.stompClient.disconnect(() => {
                // Disconnect callback logic (if needed)
            });
        }
    },

    sendMessage: (message: string) => {
        WebSocketService.stompClient?.send('/app/send', {}, message);
    },

    subscribeToMessages: (callback: (message: string) => void) => {
        WebSocketService.stompClient?.subscribe('app/user/queue/specific-user'
            + '-user' + WebSocketService.sessionId,  (response) => {
            callback(response.body);
        });
    },
};

export default WebSocketService;