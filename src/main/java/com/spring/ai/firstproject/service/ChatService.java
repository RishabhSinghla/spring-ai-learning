package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.entity.Tut;
import reactor.core.publisher.Flux;

public interface ChatService {

    String chat(String query);

    String chatTemplate();

    Flux<String> streamChat(String query);
}
