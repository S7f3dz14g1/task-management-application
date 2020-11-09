package com.example.requestapp.ui.login;

import android.content.Context;
import android.content.Intent;

import com.example.requestapp.Utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.ConnectionHelper;
import com.example.requestapp.model.StringHelper;
import com.example.requestapp.model.User;
import com.example.requestapp.ui.fragment.TaskActivity;


public class LoginActivityPresenter extends Lisner implements LoginActivityContract.Presenter {

    private LoginActivityContract.View view;
    private Database database;
    private User user;
    private Context context;

    public LoginActivityPresenter(LoginActivityContract.View view) {
        this.view = view;
        user = new User();
        database = new Database();
    }

    @Override
    public void onLoginClicked(Context context, final String nickName, final String password) {
        setContext(context);
        view.onProcessStart();
        setUser(nickName, password);
        if (!ConnectionHelper.checkConnetion()) {
            onLoginFailure(Config.NETWORK_ERROR);
        } else if (StringHelper.loginDateIsEmpty(nickName, password)) {
            onLoginFailure(Config.EMPTY_LOGIN_DATE_ERROR);
        } else if (!StringHelper.lenghPassword(password)) {
            onLoginFailure(Config.PASSWORD_LENGH_ERROR);
        } else {
            setDatabase(new Database());
            database.onLogin(user);
        }
    }

    @Override
    public boolean isLoggedIn() {
        return database.getUser() != null;
    }

    private void onLoginFailure(String message) {
        view.onLoginFailure(message);
        view.onProcessEnd();
    }

    private void startTaskActivity(String nick) {
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(Config.NICKNAME_NODE, nick);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onSuccess() {
        view.onLoginSuccessful(Config.SUCCESS_LOGIN_MESSAGE);
        view.onProcessEnd();
        startTaskActivity(user.getNickName());
    }

    @Override
    public void onFailure() {
        view.onLoginFailure(Config.PASSWORD_ERROR);
        view.onProcessEnd();
    }

    private void setUser(String name, String password) {
        user.setNickName(name);
        user.setPassword(password);
    }

    private void setContext(Context context) {
        this.context = context;
    }

    public void setDatabase(Database database) {
        this.database = database;
        this.database.setLisner(this);
    }
}
