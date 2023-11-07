package botCommand;

import TrekkerBot.EchoJavaTelegramBot;
import user.UserRepository;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {
    void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository);
}
