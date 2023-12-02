package botCommand;
import TrekkerBot.EchoJavaTelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import user.UserRepository;
import user.task.UserTask;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


public class EditTask implements BotCommand{
    public void execute(Update update, EchoJavaTelegramBot bot, UserRepository userRepository) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        ArrayList<String> buttonLabels = new ArrayList<String>();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        User user = userRepository.GetUser(update.getMessage().getFrom().getId().toString());
        Collection<UserTask> userTask = userRepository.GetUserCreatorTask(user);
        for (UserTask element : userTask) {
            buttonLabels.add(element.GetDetails());
        }
        for (String label : buttonLabels) {
            ArrayList<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(label);
            button.setCallbackData("task");
            rowInline.add(button);
            rowsInline.add(rowInline);
        }
        markupInline.setKeyboard(rowsInline);
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText("Выберите задачу");
        message.setReplyMarkup(markupInline);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}