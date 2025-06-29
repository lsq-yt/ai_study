package com.qt.ai_study.controller;


import com.qt.ai_study.service.IChatService;
import jakarta.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Author lsq
 * @Date 2025/6/29 18:05
 * @Description
 */
@RestController
@RequestMapping("api")
public class ChatController {

    Logger log = LoggerFactory.getLogger(ChatController.class);

    @Resource
    private IChatService chatService;
    @Resource
    private ChatClient chatClient;

    /**
     * 聊天
     *
     * @return
     * @Param message
     */
    @RequestMapping(value = "chat", method = {RequestMethod.GET})
    public ChatResponse chat(@RequestParam String message) {
        log.info("用户输入：{}", message);
        return chatService.chat(message);
    }

    /**
     * 简单对话
     *
     * @return
     * @Param message
     */
    @RequestMapping(value = "chatV2", method = {RequestMethod.GET})
    public String chatV2(String message) {
        log.info("用户输入：{}", message);
        //prompt:   存放提示词
        return chatClient.prompt()
                // 添加用户输入
                .user(message)
                //请求大模型
                .call()
                //返回最终的文本结果
                .content();
    }
    /**
     * 流式对话
     *
     * @return
     * @Param message
     */
    @RequestMapping(value = "chatStream", method = {RequestMethod.GET})
    public Flux<String> chatStream(String message) {
        log.info("用户输入：{}", message);
        return chatClient.prompt().user(message).stream().content();

    }
}
