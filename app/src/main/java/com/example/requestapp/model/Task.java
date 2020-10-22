package com.example.requestapp.model;

public class Task {

    private String type;
    private String descryption;
    private String nick;

    public Task() {
    }

    public Task(String type, String descryption) {
        this.type = type;
        this.descryption = descryption;
    }

    public Task(String nick, String type, String descryption) {
        this.type = type;
        this.descryption = descryption;
        this.nick = nick;
    }

    public Task(String descryption) {
        this.descryption = descryption;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }
}
