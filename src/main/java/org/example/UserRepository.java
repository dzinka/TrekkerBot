package org.example;

import java.util.HashMap;

public class UserRepository {

    private HashMap<String, User> dbUser;
    private HashMap<String, String> dbUserNameID;
    private HashMap<String, UserTask> dbTask;
    public UserRepository()
    {
        dbUser = new HashMap<>();
        dbTask = new HashMap<>();
        dbUserNameID = new HashMap<>();
    }

    public void createUser(String userId, String userName, String chatId)
    {
        if (dbTask.containsKey(userId))
            return;
        User newUser = new User(userId, userName, chatId);
        dbUser.put(userId, newUser);
        dbUserNameID.put(userName, userId);
    }
    public User GetUser(String userId)
    {
        return dbUser.get(userId);
    }

    public String GetUserIdFromName(String userName){
        if (!dbUserNameID.containsKey(userName)) return "0";
        return dbUserNameID.get(userName);
    }

    public void CreateUserTask(UserTask newUserTask)
    {
        dbTask.put(newUserTask.GetResponsible().GetUserId(), newUserTask);
    }
    public UserTask GetUserTask(User user)
    {
        return dbTask.get(user.GetUserId());
    }

}
