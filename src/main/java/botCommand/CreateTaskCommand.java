package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import user.User;
import user.UserRepository;
import user.task.UserTask;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CreateTaskCommand implements BotCommand {
    private String chatId;

    private TelegramLongPollingBot bot;
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        String messageText = update.getMessage().getText();
        String userId = update.getMessage().getFrom().getId().toString();
        String[] parts = messageText.split(" ", 3);
        String responsibleId = userRepository.GetUserIdFromName(parts[1]);
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        if (responsibleId == "0"){
            message.setText("А нельзя, пользователь не давал соглашения :)");
        }
        else {
            User responsible = userRepository.GetUser(responsibleId);
            UserTask newUserTask = new UserTask(userRepository.GetUser(userId), responsible, parts[2]);
            userRepository.CreateUserTask(newUserTask);
            message.setText("Задача успешно добавлена");
        }
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
