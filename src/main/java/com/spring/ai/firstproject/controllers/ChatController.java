package com.spring.ai.firstproject.controllers;

import com.spring.ai.firstproject.entity.Tut;
import com.spring.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String q){
       return ResponseEntity.ok(chatService.chat(q));
    }

    @GetMapping("/stream-chat")
    public ResponseEntity<Flux> streamChat(@RequestParam("q") String query){
        return ResponseEntity.ok(this.chatService.streamChat(query));
    }
}
