package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import TrekkerBot.SendResponse;
import user.UserRepository;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand implements BotCommand {

    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());// добавляем параметр в объект сообщение
        message.setText("/create_task - добавление новой задачи\n"+
                "/my_task - вывод ваших задача\n" +
                "/edit_task - редактирование зада, вами созданных\n");
        SendResponse send = new SendResponse();
        send.send(message, bot);
    }
}
