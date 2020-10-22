package com.example.requestapp.database;

import com.example.requestapp.model.Task;
import com.example.requestapp.model.User;

public interface DAO {

    void onLogin( User user);

    void signup(User user);

    void addTask(Task task,int sizeList);

    void getListTask(String nick,String pioryty);

}
