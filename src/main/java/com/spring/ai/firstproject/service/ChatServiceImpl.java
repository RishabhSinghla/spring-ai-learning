package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

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

    public String chatTemplate(){

        // 1st way
//        // first step
//        PromptTemplate strTemplate = PromptTemplate.builder().template("What is {techName}? tell me also about {exampleName}").build();
//
//        // render the template
//        String renderedMessage = strTemplate.render(Map.of("techName", "java", "exampleName", "spring exception"));
//
//        Prompt prompt = new Prompt(renderedMessage);

        // 2nd way
//        var systemPromptTemplate = SystemPromptTemplate.builder()
//                .template("You are a helpful coding assistant.")
//                .build();
//
//        var systemMessage = systemPromptTemplate.createMessage();
//
//        var userPromptTemplate = PromptTemplate.builder().template("What is {techName}? Tell me all about {techExample}").build();
//
//        var userMessage = userPromptTemplate.createMessage(Map.of(
//                "techName", "java",
//                "techExample", "spring exception"
//        ));
//
//        Prompt prompt = new Prompt(systemMessage, userMessage);
//        return this.chatClient.prompt(prompt).call().content();

        // 3rd way Fluent API
//        return this.chatClient
//                .prompt()
//                .system(system -> system.text("You are a helpful coding assistant"))
//                .user(user -> user.text("What is {techName}? Tell me all about {techExample}").param("techName", "java").param("techExample", "spring exception"))
//                .call()
//                .content();

        //4th Way from resource
        return this.chatClient
                .prompt()
                .system(system -> system.text(this.systemMessage))
                .user(user -> user.text(this.userMessage).param("concept", "java"))
                .call()
                .content();
    }
}
