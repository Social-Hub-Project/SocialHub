package com.application.socialhub.controller;

import com.application.socialhub.config.WebSocketHandler;
import com.application.socialhub.dao.UserInfoDAO;
import com.application.socialhub.dto.SendFullMessageRequest;
import com.application.socialhub.dto.SendMessageRequest;
import com.application.socialhub.model.UserInfo;
import com.application.socialhub.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/app")
public class ChatRoomController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JWTUtil jwtUtil;
    private final UserInfoDAO userInfoDAO;
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    public ChatRoomController(SimpMessagingTemplate simpMessagingTemplate, JWTUtil jwtUtil, UserInfoDAO userInfoDAO) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.jwtUtil = jwtUtil;
        this.userInfoDAO = userInfoDAO;
    }

    @MessageMapping("/app/chatRoom")
    public void sendSpecific(@Payload SendMessageRequest msg) throws Exception {
        logger.warn("sendSpecific "+msg);
        String email = jwtUtil.getSubject(msg.token());
        UserInfo userInfo = userInfoDAO.findUserInfoByEmail(email);
        logger.warn("sendSpecific "+msg);
        SendFullMessageRequest out = new SendFullMessageRequest(userInfo.getName()+" "+userInfo.getSurname(), msg.content(), new SimpleDateFormat("HH:mm:ss").format(new Date()));
        simpMessagingTemplate.convertAndSendToUser(msg.receiver(), "/app/user/queue/specific-user", out);
    }
}
