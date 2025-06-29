package com.qt.ai_study.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lsq
 * @Date 2025/6/29 18:11
 * @Description
 */
@Configuration
public class AIConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                //设置默认角色 方法中加入角色的提示词
                .defaultSystem("你是一名Java开发，你精通spring全家桶、各种中间件以及各种数据库，你的名字叫齐腾智能管家")
                .build();

    }
}
