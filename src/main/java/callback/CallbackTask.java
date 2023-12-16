package callback;

import TrekkerBot.EchoJavaTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.User;
import user.UserRepository;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import user.task.UserTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CallbackTask implements BotCallback {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        ArrayList<String> buttonLabels = new ArrayList<String>();
        buttonLabels.add("action дедлайн");
        buttonLabels.add("action статус");
        buttonLabels.add("action выполняющий");
        buttonLabels.add("action описание");
        String messageText = "Выберите что хотите изменить";
        SendCallback send = new SendCallback();
        send.execute(update, bot, userRepository, buttonLabels, messageText);
    }
}
