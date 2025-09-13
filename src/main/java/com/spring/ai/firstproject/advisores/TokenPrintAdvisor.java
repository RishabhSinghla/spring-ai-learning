package com.spring.ai.firstproject.advisores;


import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor {

    private static final Logger logger = Logger.getLogger(TokenPrintAdvisor.class.getName());

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        this.logger.info("My token print advisor is called: ");
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        this.logger.info("Response received from the model: ");
        this.logger.info("Prompt Token: " + chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        this.logger.info("Completion Token: " + chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        this.logger.info("Total Token: " + chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());

        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        Flux<ChatClientResponse> chatClientResponseFlux = streamAdvisorChain.nextStream(chatClientRequest);
        return chatClientResponseFlux;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
