package com.example.sportsadda.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String name ;
    public String email;
    public String username;

    public User(){

    }

    public User(String name,String username, String email){
        this.name = name;
        this.username  = username;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}

