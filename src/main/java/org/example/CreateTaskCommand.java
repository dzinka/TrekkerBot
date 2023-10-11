package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class CreateTaskCommand implements BotCommand{
    private String chatId;

    private TelegramLongPollingBot bot;
    @Override
    public void ChatIdBot(String chatId_, TelegramLongPollingBot bot_) {
        chatId = chatId_;
        bot = bot_;
    }
    @Override
    public void execute() {
        // Логика для команды /createtaskcommand

    }
}
