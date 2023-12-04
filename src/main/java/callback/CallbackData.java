package callback;

import TrekkerBot.EchoJavaTelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import user.UserRepository;

public class CallbackData implements BotCallback {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {

    }
}
