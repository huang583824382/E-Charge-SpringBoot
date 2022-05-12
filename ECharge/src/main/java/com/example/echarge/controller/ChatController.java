package com.example.echarge.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    //获取聊天列表（定期轮询，返回未读消息）
    //获取与某人的聊天记录（获取完整聊天记录）
    //获取未读消息（只返回未读消息，实时聊天时轮询）
    //发送消息
    //
}
