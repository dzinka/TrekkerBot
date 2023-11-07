package botCommand;

import TrekkerBot.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class BotCommandHandler {
    private Map<String, BotCommand> commandMap;

    public BotCommandHandler() {
        commandMap = new HashMap<>();
        commandMap.put("/start", new StartCommand());
        commandMap.put("/help", new HelpCommand());
        commandMap.put("/create_task", new CreateTaskCommand());
        commandMap.put("/my_task", new MyTaskCommand());
    }

    public void processCommand(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        String userMessage = update.getMessage().getText();
        String[] parts = userMessage.split(" ", 2);
        String command = parts[0];
        BotCommand botCommand = commandMap.get(command);
        if (botCommand != null) {
            botCommand.execute(update, bot, userRepository);
        } else {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Неизвестная команда. Введите /help для получения справки.");
            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
