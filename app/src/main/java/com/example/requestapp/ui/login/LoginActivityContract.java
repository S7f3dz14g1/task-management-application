package com.example.requestapp.ui.login;

import android.content.Context;

public interface LoginActivityContract {

    interface Presenter {

        void onLoginClicked(Context context, String nickName, String password);

        boolean isLoggedIn();

    }

    interface View {

        void onLoginSuccessful(String message);

        void onLoginFailure(String message);

        void onProcessStart();

        void onProcessEnd();

    }
}
