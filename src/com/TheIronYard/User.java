package com.TheIronYard;

/**
 * Created by branden on 2/24/16 at 11:13.
 */
public class User {

    String name, password;
    int id;


    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}