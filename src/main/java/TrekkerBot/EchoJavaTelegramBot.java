package TrekkerBot;

import botCommand.BotCommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.UserRepository;
import user.session.UserSession;

import java.util.ArrayList;
import java.util.List;

public class EchoJavaTelegramBot extends TelegramLongPollingBot {
    final String botName;
    final String botToken;
    private UserRepository userRepository;

    public EchoJavaTelegramBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
        this.userRepository = new UserRepository();
        setMyCommands();
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText.equals("/start")){
                BotCommandHandler botHandler = new BotCommandHandler();
                botHandler.processCommand(update, this, userRepository);
            }
            else {
                String userId = update.getMessage().getFrom().getId().toString();
                userRepository.getUserSession(userId).handleMessage(update, userRepository, this);
            }
        } else if (update.hasCallbackQuery()) {
            String userId = update.getCallbackQuery().getFrom().getId().toString();
            userRepository.getUserSession(userId).handleMessage(update, userRepository, this);
        }
    }
    public void setMyCommands() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "начать"));
        listOfCommands.add(new BotCommand("/help", "информация по боту"));
        listOfCommands.add(new BotCommand("/craete_task", "nickname назавание задачи"));
        listOfCommands.add(new BotCommand("/my_task", "ваши задачи"));
        listOfCommands.add(new BotCommand("/edit_task", "редактирование вами созданной задачи"));
        listOfCommands.add(new BotCommand("/delete_task", "удалить вами созданную заадчу"));

        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(listOfCommands);

        try {
            execute(setMyCommands);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
