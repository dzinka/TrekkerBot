package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import TrekkerBot.SendResponse;
import user.User;
import user.UserRepository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.task.UserTask;

import java.util.Collection;

public class MyTaskCommand implements BotCommand {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        String userId = update.getMessage().getFrom().getId().toString();
        User user = userRepository.GetUser(userId);
        SendMessage message = new SendMessage();
        message.setChatId(user.GetChatId());
        Collection<UserTask> userTasks = userRepository.GetUserTask(user);
        String answer = "";
        if (userTasks.isEmpty()){
            answer = " У вас нет задач";
            message.setText(answer);
        }
        for (UserTask temp : userTasks)
        {
            answer = answer + temp.GetDetails() + " ";
        }
        message.setText(answer);
        SendResponse send = new SendResponse();
        send.send(message, bot);
    }
}
