package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import TrekkerBot.SendResponse;
import callback.SendCallback;
import user.User;
import user.UserRepository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.task.UserTask;

import java.util.ArrayList;
import java.util.Collection;

public class MyTaskCommand implements BotCommand {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {

        String userId = update.getMessage().getFrom().getId().toString();
        User user = userRepository.GetUser(userId);
        ArrayList<String> buttonLabels = new ArrayList<String>();
        Collection<UserTask> userTask = userRepository.GetUserCreatorTask(user);
        for (UserTask element : userTask) {
            buttonLabels.add("seeTask " + element.GetIdentifier());
        }
        String messageText = "Ваши задачи";
        SendCallback send = new SendCallback();
        send.execute(update, bot, userRepository, buttonLabels, messageText);
    }
}
