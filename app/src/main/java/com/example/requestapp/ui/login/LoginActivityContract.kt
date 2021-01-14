package com.example.requestapp.ui.login

import android.content.Context

interface LoginActivityContract {
    interface Presenter {
        fun onLoginClicked(context: Context?, nickName: String?, password: String?)
        fun isLoggedIn(): Boolean
    }

    interface View {
        fun onLoginSuccessful(message: String?)
        fun onLoginFailure(message: String?)
        fun onProcessStart()
        fun onProcessEnd()
    }
}