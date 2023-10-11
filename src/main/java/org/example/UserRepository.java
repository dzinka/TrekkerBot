package org.example;

import java.util.HashMap;

public class UserRepository {

    private HashMap<String, User> db;
    public UserRepository(){
        db = new HashMap<>();
    }
    public void createUser(String userId_, String userName_, String chatId_){

        User newUser = new User(userName_, chatId_);
        db.put(userId_, newUser);
    }
    public User getUser(String userId_){

        return db.get(userId_);
    }
}
