package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import callback.SendCallback;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.User;
import user.UserRepository;
import user.task.UserTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EditTaskCommand implements BotCommand{
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        ArrayList<String> buttonLabels = new ArrayList<String>();
        User user = userRepository.GetUser(update.getMessage().getFrom().getId().toString());
        Collection<UserTask> userTask = userRepository.GetUserCreatorTask(user);
        for (UserTask element : userTask) {
            buttonLabels.add("task " + element.GetIdentifier());
        }
        String messageText = "Выберите задачу";
        SendCallback send = new SendCallback();
        send.execute(update, bot, userRepository, buttonLabels, messageText);

    }
}
