package callback;

import TrekkerBot.EchoJavaTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class CallbackAction extends CallbackTask{
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        ArrayList<String> buttonLabels = new ArrayList<String>();
        buttonLabels.add("data Ввести новые данные");
        buttonLabels.add("task Вернуться назад");
        String messageText = "Выберите действие";
        SendCallback send = new SendCallback();
        send.execute(update, bot, userRepository, buttonLabels, messageText);
    }
}
