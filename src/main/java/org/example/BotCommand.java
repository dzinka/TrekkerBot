package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {
    void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository);
}
