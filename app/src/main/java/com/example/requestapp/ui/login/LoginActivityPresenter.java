package com.example.requestapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.requestapp.Utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.User;
import com.example.requestapp.ui.fragment.TaskActivity;


public class LoginActivityPresenter extends Lisner implements LoginActivityContract.Presenter{

    private LoginActivity view;
    private Database database;

    public LoginActivityPresenter(LoginActivity view) {
        this.view = view;
        database=new Database();
        database.setLisner(this);
    }


    @Override
    public void onLoginClicked(Context context,final String nickName, final String password) {
        view.onProcessStart();
        if(!checkConnection()){
            onLoginFailure("network problem");
        }else if(nickName.isEmpty()||password.isEmpty()) {
            onLoginFailure("Logni and password cannot be empty");
        }else if(lengthPassword(password)<5){
            onLoginFailure("Password length should be greater than 6");
        }else {
            database.onLogin(new User(nickName,password));
        }
    }

    private void onLoginFailure(String message){
        view.onLoginFailure(message);
        view.onProcessEnd();
    }

    public boolean checkConnection() {
        //sprawdzenie czy jest połączenie z internetem
    return true;
}
    public int lengthPassword(String password){
        return  password.length();
    }

    private void startActivity(Context context,String nick){
        Intent intent=new Intent(context, TaskActivity.class);
        intent.putExtra(Config.NICKNAME_NODE,nick);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onSuccess() {
        view.onLoginSuccessful("Login succesful");
        view.onProcessEnd();
        startActivity(view.getApplicationContext(),view.nick.getText().toString());
    }

    @Override
    public void onFailure() {
        view.onLoginFailure("The password provided is wrong");
        view.onProcessEnd();
    }
}
