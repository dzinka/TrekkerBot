package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
//import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

//@Component
public class EchoJavaTelegramBot extends TelegramLongPollingBot {
    final String botName;
    final String botToken;
    private UserRepository userRepository = new UserRepository();
    public EchoJavaTelegramBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "Привет!"));
        listofCommands.add(new BotCommand("/help", "information"));
        listofCommands.add(new BotCommand("/create_task", "get a welcome message"));
        listofCommands.add(new BotCommand("/my_task", "information"));
        try{
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(),null));
        }
        catch (TelegramApiException e){
            //log.error("Error");
        }
        //listofCommands.add(new BotCommand("/help", "information"));
        try{
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(),null));
        }
        catch (TelegramApiException e){
            //log.error("Error");
        }
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

            String userId = update.getMessage().getFrom().getId().toString();
            String chatId = update.getMessage().getChatId().toString();
            String userName = update.getMessage().getFrom().getUserName();
            userRepository.createUser(userId, userName, chatId);

            if (messageText.startsWith("/")) {
                BotCommandHandler botHandler = new BotCommandHandler();
                String userMessage = update.getMessage().getText();
                botHandler.processCommand(update, this, userRepository);
            }
            else if (update.hasCallbackQuery()){
                String callbackData = update.getCallbackQuery().getData();
                // Проверяем, какая кнопка была нажата
                if (callbackData.equals("Кнопка 1")) {
                    sendResponseMessage(update.getCallbackQuery().getMessage().getChatId().toString(), "Вы нажали кнопку 1");
                } else if (callbackData.equals("Кнопка 2")) {
                    sendResponseMessage(update.getCallbackQuery().getMessage().getChatId().toString(), "Вы нажали кнопку 2");
                }

            }
            else{
                String chat_id = update.getMessage().getChatId().toString();
                sendInlineKeyboard(chat_id);

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
    private void sendResponseMessage(String chatId, String text) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(chatId);
        responseMessage.setText(text);


        ReplyKeyboardMarkup keyboardMarkup= new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row  = new KeyboardRow();

        row.add("/start");
        row.add("/help");
        row.add("??????");
        keyboardRows.add(row);


        row = new KeyboardRow();
        row.add("/help");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        responseMessage.setReplyMarkup(keyboardMarkup);

        try {
            execute(responseMessage); // Отправка сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



        public void sendInlineKeyboard(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите действие:");

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineButton("Кнопка 1", "callback_data_1"));
        row1.add(new InlineButton("Кнопка 2", "callback_data_2"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineButton("Кнопка 3", "callback_data_3"));
        row2.add(new InlineButton("Кнопка 4", "callback_data_4"));

        keyboard.add(row1);
        keyboard.add(row2);

        markupKeyboard.setKeyboard(keyboard);
        message.setReplyMarkup(markupKeyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

