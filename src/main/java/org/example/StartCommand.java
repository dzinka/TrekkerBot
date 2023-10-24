package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements BotCommand {

        private String chatId;

        private TelegramLongPollingBot bot;
        @Override
        public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
                String chatId = update.getMessage().getChatId().toString();
                System.out.println(chatId);
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Привет! Я TrekkerBot, могу помочь тебе " +
                        "создать новые задачи. Назначай ответственных,  " +
                        "ставь дедлайны. /help - если нужна помощь " );
                try {
                        // Отправка сообщения
                        bot.execute(message);
                } catch (TelegramApiException e) {
                        e.printStackTrace();
                }
        }

}

