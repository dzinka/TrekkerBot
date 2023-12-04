package user.session;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UserSession {
    private String chatId;
    private String currentState;

    public UserSession(String chatId) {
        this.chatId = chatId;
        this.currentState = "start";
    }

    public void handleMessage(SendMessage message) {
        // Логика обработки сообщения и обновления состояния
        // в зависимости от текущего состояния сессии
    }

}
