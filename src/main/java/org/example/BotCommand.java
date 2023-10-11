package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public interface BotCommand {
    void execute();
    void ChatIdBot(String chatId_, TelegramLongPollingBot bot_);
}
