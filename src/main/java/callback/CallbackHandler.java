package callback;

import TrekkerBot.EchoJavaTelegramBot;
import botCommand.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.User;
import user.UserRepository;
import user.task.UserTask;

import java.util.*;

public class CallbackHandler {
    private Map<String, String> callMap;
    private Map<String, BotCallback> callbackMap;

    public CallbackHandler() {
        callbackMap = new HashMap<>();
        callbackMap.put("action", new CallbackAction());
        callbackMap.put("task", new CallbackTask());
    }
    public void processCommand(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        BotCallback botCallback = callbackMap.get(data);
        if (botCallback != null) {
            botCallback.execute(update, bot, userRepository);
        }
    }
}
