package callback;

import TrekkerBot.EchoJavaTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.User;
import user.UserRepository;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import user.task.UserTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CallbackTask implements BotCallback {
    @Override
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String chatId = callbackQuery.getMessage().getChatId().toString();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        ArrayList<String> buttonLabels = new ArrayList<String>();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        buttonLabels.add("дедлайн");
        buttonLabels.add("статус");
        buttonLabels.add("выполняющий");
        buttonLabels.add("описание");
        for (String label : buttonLabels) {
            ArrayList<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(label);
            button.setCallbackData("action " + label);
            rowInline.add(button);
            rowsInline.add(rowInline);
        }
        markupInline.setKeyboard(rowsInline);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите что хотите изменить");
        message.setReplyMarkup(markupInline);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
