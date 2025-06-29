package com.qt.ai_study.service.impl;

import com.qt.ai_study.service.IChatService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lsq
 * @Date 2025/6/29 18:05
 * @Description
 */
@Service
public class ChatServiceImpl implements IChatService {
    Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Resource
    private OpenAiChatModel chatModel;

    private final List<Message> chatHistoryList = new ArrayList<>();

    //当 Spring 容器创建 IChatServiceImpl 对象时，会自动调用 init 方法
    @PostConstruct
    public void init() {
        chatHistoryList.add(new SystemMessage("You are a helpful assistant."));
    }

    /**
     * 聊天
     * @param message
     * @return
     */
    @Override
    public ChatResponse chat(String message) {
        chatHistoryList.add(new UserMessage(message));

        Prompt prompt = new Prompt(chatHistoryList);
        ChatResponse chatResponse = chatModel.call(prompt);
        if (chatResponse.getResult() != null && chatResponse.getResult().getOutput() != null) {
            chatHistoryList.add(chatResponse.getResult().getOutput());
        }
        return chatResponse;
    }

}
