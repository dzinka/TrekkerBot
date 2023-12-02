package TrekkerBot;

import botCommand.BotCommandHandler;
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
    private UserRepository userRepository = new UserRepository();

    public EchoJavaTelegramBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
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
        System.out.println(update);
        Long longChatId = update.getMessage().getChatId();
        if (update.getCallbackQuery() != null){
            System.out.println("gggg");
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            String chatIdd = callbackQuery.getMessage().getChatId().toString();
            if (data.equals("task")){
                sendResponse(chatIdd, "Вы нажали на инлайн-кнопку");
            }
        }
        else if ((longChatId > 0) && (update.hasMessage() && update.getMessage().hasText())) {
            String chatId = update.getMessage().getChatId().toString();
            String messageText = update.getMessage().getText();
            String userId = update.getMessage().getFrom().getId().toString();
            String userName = update.getMessage().getFrom().getUserName();
            userRepository.createUser(userId, userName, chatId);
            if (messageText.startsWith("/")) {
                BotCommandHandler botHandler = new BotCommandHandler();
                botHandler.processCommand(update, this, userRepository);
            }else {
                String chat_id = update.getMessage().getChatId().toString();

                SendMessage message = new SendMessage();
                message.setChatId(chat_id);
                message.setText(messageText);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
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
