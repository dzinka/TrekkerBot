package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EchoJavaTelegramBot extends TelegramLongPollingBot {
    final String botName;
    final String botToken;
    private UserRepository userRepository = new UserRepository();

    public EchoJavaTelegramBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            String userId = update.getMessage().getFrom().getId().toString();
            String chatId = update.getMessage().getChatId().toString();
            String userName = update.getMessage().getFrom().getUserName();
            userRepository.createUser(chatId, userId, userName);

            if (messageText.startsWith("/")) {
                BotCommandHandler botHandler = new BotCommandHandler();
                String userCommand = update.getMessage().getText(); // сохраняем в переменную команду пользавателя
                botHandler.processCommand(userCommand, chatId, this);
            }
            else{
                String chat_id = update.getMessage().getChatId().toString();    // Создаем переменную равную ид чата присланного сообщения

                SendMessage message = new SendMessage(); // Создаем обект-сообщение
                message.setChatId(chat_id);              // Передаем чат ид
                message.setText(messageText);           // Передаем эхо сообщение

                try {
                    execute(message);                   // Отправляем обект-сообщение пользователю
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

