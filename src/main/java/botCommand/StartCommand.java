package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import user.UserRepository;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements BotCommand {

        private String chatId;

        private TelegramLongPollingBot bot;
        @Override
        public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
                String chatId = update.getMessage().getChatId().toString();
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Привет!" );
                try {
                        // Отправка сообщения
                        bot.execute(message);
                } catch (TelegramApiException e) {
                        e.printStackTrace();
                }
        }

}

