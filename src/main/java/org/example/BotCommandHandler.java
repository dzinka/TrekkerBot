package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;
import java.util.Map;

public class BotCommandHandler {
    private Map<String, BotCommand> commandMap;

    public BotCommandHandler() {
        commandMap = new HashMap<>();
        commandMap.put("/start", new StartCommand());
        commandMap.put("/help", new HelpCommand());
        commandMap.put("/createtask", new CreateTaskCommand());
        // Добавьте другие команды и соответствующие им классы
    }

    public void processCommand(String command, String chatId, TelegramLongPollingBot bot) {
        BotCommand botCommand = commandMap.get(command);
        if (botCommand != null) {
            botCommand.ChatIdBot(chatId, bot);
            botCommand.execute();
        } else {
            System.out.println("Неизвестная команда. Введите /help для получения справки.");
        }
    }
}
