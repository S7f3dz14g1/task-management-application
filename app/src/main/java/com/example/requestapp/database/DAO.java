package com.example.requestapp.database;

import com.example.requestapp.model.Task;
import com.example.requestapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface DAO {

    void onLogin( User user);

    void signup(User user);

    void addTask(Task task,int sizeList);

    void getListTask(String nick,String pioryty);

    FirebaseUser getUser();

    String getUserNick();

}
