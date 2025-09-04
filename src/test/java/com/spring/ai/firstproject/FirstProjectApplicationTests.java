package com.spring.ai.firstproject;

import com.spring.ai.firstproject.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstProjectApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private ChatService chatService;

    @Test
    void testTemplateRenderer(){

        System.out.println("Template Renderer");

        var output = this.chatService.chatTemplate();

        System.out.println(output);
    }

}
