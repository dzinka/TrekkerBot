package user.session;

import TrekkerBot.EchoJavaTelegramBot;
import TrekkerBot.SendResponse;
import botCommand.BotCommandHandler;
import callback.BotCallback;
import callback.CallbackAction;
import callback.CallbackTask;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import user.User;
import user.UserRepository;
import user.task.UserTask;

import java.util.Collection;
import java.util.HashMap;

public class UserSession {
    private String userId;
    private String currentState;
    private HashMap<String, String> callback;

    public UserSession(String userId) {
        this.userId = userId;
        this.currentState = "start";
        this.callback = new HashMap<>();
    }

    public String getUserId(){ return this.userId; }

    public String getCurrentState(){
        return this.currentState;
    }
    public void handleMessage(Update update, UserRepository userRepository, EchoJavaTelegramBot bot) {
        /*
        if (update.hasCallbackQuery() && currentState.equals("start")) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            if (data.equals("data")){
                BotCallback botCallback = new CallbackData();
                botCallback.execute(update, bot, userRepository);
                this.currentState = "data";
            }
            else if (data.startsWith("task")){
                callback.put("task", data);
                BotCallback botCallback = new CallbackTask();
                botCallback.execute(update, bot, userRepository);
            }
            else if (data.startsWith("action")){
                callback.put("action", data);
                BotCallback botCallback = new CallbackAction();
                botCallback.execute(update, bot, userRepository);
            }
        }
        else if (this.currentState.equals("data")){
            String message = update.getMessage().getText();
            User user = userRepository.GetUser(update.getMessage().getFrom().getId().toString());
            Collection<UserTask> userTask = userRepository.GetUserCreatorTask(user);
            for (UserTask element : userTask) {
                if (element.GetDetails().equals(callback.get("task").substring(5))){
                    element.ChangeDescription(message);
                }
            }
        }*/
        if (update.hasCallbackQuery() && currentState.equals("start")) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            if (data.equals("data")){
                SendMessage message = new SendMessage();
                String chatId = callbackQuery.getMessage().getChatId().toString();
                message.setChatId(chatId);
                message.setText("Ведите новое описание");
                SendResponse send = new SendResponse();
                send.send(message, bot);
                this.currentState = "data";
            }
            else if (data.startsWith("task")){
                callback.put("task", data);
                BotCallback botCallback = new CallbackTask();
                botCallback.execute(update, bot, userRepository);
            }
            else if (data.startsWith("action")){
                callback.put("action", data);
                BotCallback botCallback = new CallbackAction();
                botCallback.execute(update, bot, userRepository);
            }
        }

        else if (update.hasMessage() && update.getMessage().hasText() && currentState.equals("data")) {
            String message = update.getMessage().getText();
            User user = userRepository.GetUser(update.getMessage().getFrom().getId().toString());
            Collection<UserTask> userTask = userRepository.GetUserCreatorTask(user);
            for (UserTask element : userTask) {
                if (element.GetDetails().equals(callback.get("task").substring(5))){
                    element.ChangeDescription(message);
                }
            }
            currentState = "start";
        }
        else if (update.hasMessage() && update.getMessage().hasText() && currentState.equals("start")) {
            BotCommandHandler botcommand = new BotCommandHandler();
            botcommand.processCommand(update, bot, userRepository);
        }
    }

}
