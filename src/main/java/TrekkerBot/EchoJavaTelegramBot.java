package TrekkerBot;

import botCommand.BotCommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.UserRepository;
import user.session.UserSession;

public class EchoJavaTelegramBot extends TelegramLongPollingBot {
    final String botName;
    final String botToken;
    private UserRepository userRepository;

    public EchoJavaTelegramBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
        this.userRepository = new UserRepository();
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

    private void sendResponse(String chatIdd, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatIdd);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
