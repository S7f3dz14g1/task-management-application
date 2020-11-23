package com.example.requestapp.database;

import android.net.Uri;

import com.example.requestapp.model.Task;
import com.example.requestapp.model.User;
import com.google.firebase.auth.FirebaseUser;

public interface DAO {

    void onLogin(User user);

    void signup(User user);

    void addAvailableTask(Task task, int sizeList);

    void addCompletedTask(Task task, int sizeList);

    void getListAvilableTask(String nick, String pioryty);

    void getListCompletedTask();

    FirebaseUser getUser();

    String getUserNick();

    void signOut();

    void uploadImage(Uri uri, String key);

    void getKeyUserImage(String nickName);

    void getUserImage(String key);

    void moveAvailableTaskToCompletedTask(Task task,String intex);

    void getSizeCompletedList();
}
