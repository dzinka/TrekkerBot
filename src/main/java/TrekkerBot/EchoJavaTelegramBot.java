package TrekkerBot;

import botCommand.BotCommandHandler;
import callback.CallbackHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.UserRepository;

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
            if (messageText.startsWith("/")){
                BotCommandHandler botHandler = new BotCommandHandler();
                botHandler.processCommand(update, this, userRepository);
            }
        } else if (update.hasCallbackQuery()) {
            CallbackHandler callbackHandler = new CallbackHandler();
            callbackHandler.processCommand(update, this, userRepository);
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
