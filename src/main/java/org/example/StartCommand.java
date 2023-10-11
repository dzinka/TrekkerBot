package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements BotCommand {

        private String chatId;

        private TelegramLongPollingBot bot;
        @Override
        public void ChatIdBot(String chatId_, TelegramLongPollingBot bot_){
                chatId = chatId_;
                bot = bot_;
        }
        @Override
        public void execute() {
                // Логика для команды /start
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Привет! Я TrekkerBot, могу помочь тебе " +
                        "создать новые задачи. Назначай ответственных,  " +
                        "определяйся со сроками, а я обязательно напомню о приближении " +
                        "дедлайнов. /help -  " +
                        "если нужна помощь " );
                try {
                        // Отправка сообщения
                        bot.execute(message);
                } catch (TelegramApiException e) {
                        e.printStackTrace();
                }
        }

}

