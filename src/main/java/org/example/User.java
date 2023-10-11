package org.example;

public class User {
    private String id;
    private String name;

    public User(String id_, String name_){
        this.id = id_;
        this.name = name_;
    }

    public String GetId(){
        return id;
    }
}
