package com.example.meruj.projectmm.user_utility;

/**
 * Created by Meruj on 2/14/2018.
 */

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {

        this.name = name;
        this.id = id;
        this.email = email;
        this.password = password;
    }
    public User( String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }
}

