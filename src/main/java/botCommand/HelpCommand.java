package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import user.UserRepository;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand implements BotCommand {
    private String chatId;
    private TelegramLongPollingBot bot;
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        // Логика для команды /help
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());// добавляем параметр в объект сообщение
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
