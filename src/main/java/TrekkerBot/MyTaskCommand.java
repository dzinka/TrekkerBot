package TrekkerBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyTaskCommand implements BotCommand {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        String userId = update.getMessage().getFrom().getId().toString();
        User user = userRepository.GetUser(userId);
        SendMessage message = new SendMessage();
        message.setChatId(user.GetChatId());
        message.setText(userRepository.GetUserTask(user).GetDetails());
        try {
            // Отправка сообщения
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
