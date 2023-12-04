package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import user.UserRepository;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements BotCommand {
        @Override
        public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
                String userId = update.getMessage().getFrom().getId().toString();
                String chatId = update.getMessage().getChatId().toString();
                String userName = update.getMessage().getFrom().getUserName();
                userRepository.createUser(userId, userName, chatId);
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Привет!" );
                try {
                        bot.execute(message);
                } catch (TelegramApiException e) {
                        e.printStackTrace();
                }
        }

}

