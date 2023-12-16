package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import callback.SendCallback;
import org.telegram.telegrambots.meta.api.objects.Update;
import user.User;
import user.UserRepository;
import user.task.UserTask;

import java.util.ArrayList;
import java.util.Collection;

public class DeleteTaskCommand implements BotCommand {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        ArrayList<String> buttonLabels = new ArrayList<String>();
        User user = userRepository.GetUser(update.getMessage().getFrom().getId().toString());
        Collection<UserTask> userTask = userRepository.GetUserCreatorTask(user);
        for (UserTask element : userTask) {
            buttonLabels.add("Deletetask " + element.GetIdentifier());
        }
        String messageText = "Выберите задачу";
        SendCallback send = new SendCallback();
        send.execute(update, bot, userRepository, buttonLabels, messageText);
    }
}
