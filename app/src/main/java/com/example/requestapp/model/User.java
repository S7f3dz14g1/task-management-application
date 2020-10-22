package com.example.requestapp.model;

public class User {

    private String NickName;
    private String Password;
    private String Key;

    public User(String nickName, String password, String key) {
        NickName = nickName;
        Password = password;
        Key = key;
    }

    public User(String nickName, String password) {
        NickName = nickName;
        Password = password;
    }

    public User() {
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
