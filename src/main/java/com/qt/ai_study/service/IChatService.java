package com.qt.ai_study.service;

import org.springframework.ai.chat.model.ChatResponse;

/**
 * @Author lsq
 * @Date 2025/6/29 18:05
 * @Description
 */
public interface IChatService {
    /**
     * 聊天
     * @param message
     * @return
     */
    ChatResponse chat(String message);
}
