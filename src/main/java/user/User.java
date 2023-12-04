package user;

import callback.CallbackHandler;

public class User {
    private String chatId;

    private String userId;
    private String userName;

    private CallbackHandler userCallbackState;

    public void CreateUserCallbackState(){
        userCallbackState = new CallbackHandler();
    }

    public User(String userId, String userName, String chatId){
        this.userId = userId;
        this.userName = userName;
        this.chatId = chatId;
    }

    public String GetChatId(){
        return chatId;
    }
    public String GetUserId(){
        return userId;
    }
    public String GetUserName(){
        return userName;
    }
}
