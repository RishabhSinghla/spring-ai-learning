package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    @Autowired
    public ChatServiceImpl(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @Override
    public String chat(String query){
//        String prompt = "tell me about virat kohli";

        // this is fluent API below

        //1st  Way
//        String content = chatClient
//                .prompt()
//                .user(prompt)
//                .system("As an expert in cricket")
//                .call()
//                .content();

        //2nd  Way
//        String content = chatClient
//                .prompt(prompt)
//                .call()
//                .content();

        //3rd  Way
//        var metaData = chatClient
//                .prompt(prompt1)
//                .call()
//                .chatResponse()
//                .getMetadata();
//
//        System.out.println(metaData);
// Ex: { id: chatcmpl-CC7aBoqFqPPhT3hE1616xOw3p59VI, usage: DefaultUsage{promptTokens=14, completionTokens=663, totalTokens=677}, rateLimit: { @type: org.springframework.ai.openai.metadata.OpenAiRateLimit, requestsLimit: 500, requestsRemaining: 499, requestsReset: PT0.12S, tokensLimit: 30000; tokensRemaining: 29991; tokensReset: PT0.018S } }

        // 4th Way
//        Tut tutorial = chatClient
//                .prompt(prompt1)
//                .call()
//                .entity(Tut.class);

        Prompt prompt1 = new Prompt(query);

        String queryStr = "As an expert in coding and programming. Always write program in java. Now reply for this question :{query}";

        var content = chatClient
                .prompt()
                .user(u -> u.text(queryStr).param("query", query))
                .call()
                .content();

        return content;
    }
}
