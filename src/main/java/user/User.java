package user;


public class User {
    private String chatId;

    private String userId;
    private String userName;



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
