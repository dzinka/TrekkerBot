package callback;

import TrekkerBot.EchoJavaTelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import user.UserRepository;

public interface BotCallback {
    void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository);
}
