package user;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import user.session.UserSession;
import user.task.UserTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private HashMap<String, User> dbUser;
    private HashMap<String, String> dbUserNameID;
    private Multimap<String, UserTask> dbTask;
    private Multimap<String, UserTask> dbTaskToResponsible;
    private Map<String, UserSession> sessions;
    public UserRepository()
    {
        dbUser = new HashMap<>();
        dbTask = ArrayListMultimap.create();
        dbTaskToResponsible = ArrayListMultimap.create();
        dbUserNameID = new HashMap<>();
        sessions = new HashMap<>();
    }

    public UserSession getUserSession(String userId){
        return  sessions.get(userId);
    }

    public void createUser(String userId, String userName, String chatId)
    {
        if (dbTask.containsKey(userId))
            return;
        User newUser = new User(userId, userName, chatId);
        dbUser.put(userId, newUser);
        dbUserNameID.put(userName, userId);
        sessions.put(userId, new UserSession(userId));
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

        dbTaskToResponsible.put(newUserTask.GetCreator().GetUserId(), newUserTask);
    }
    public Collection<UserTask> GetUserTask(User user)
    {
        return dbTask.get(user.GetUserId());
    }
    public Collection<UserTask> GetUserCreatorTask(User user){ return dbTaskToResponsible.get(user.GetUserId());}

}
