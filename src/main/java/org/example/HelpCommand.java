package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand implements BotCommand {
    private String chatId;
    private TelegramLongPollingBot bot;
    @Override
    public void ChatIdBot(String chatId_, TelegramLongPollingBot bot_){
        chatId = chatId_;
        bot = bot_;
    }
    @Override
    public void execute() {
        // Логика для команды /help
        SendMessage message = new SendMessage();
        message.setChatId(chatId);// добавляем параметр в объект сообщение
        message.setText("" +
                "" +
                "\n/createtask - добавление новой задачи");
        try {
            // Отправка сообщения
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
