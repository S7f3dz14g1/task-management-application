package com.example.requestapp.ui.login

import android.content.Context
import android.content.Intent
import com.example.requestapp.database.Database
import com.example.requestapp.iterator.Lisner
import com.example.requestapp.model.ConnectionHelper
import com.example.requestapp.model.StringHelper
import com.example.requestapp.model.User
import com.example.requestapp.ui.fragment.TaskActivity
import com.example.requestapp.utils.Config

class LoginActivityPresenter(_view: LoginActivityContract.View, _contex: Context) : Lisner(), LoginActivityContract.Presenter {

    private var database: Database = Database()
    private var user: User = User()
    private var context: Context = _contex
    private var view: LoginActivityContract.View = _view


    override fun onLoginClicked(context: Context?, nickName: String?, password: String?) {
        if (context != null) {
            setContext(context)
        }
        view.onProcessStart()
        if (nickName != null && password != null) {
            setUser(nickName, password)
        }
        if (!ConnectionHelper.checkConnetion()) {
            onLoginFailure(Config.NETWORK_ERROR)
        } else if (StringHelper.loginDateIsEmpty(nickName, password)) {
            onLoginFailure(Config.EMPTY_LOGIN_DATE_ERROR)
        } else if (!StringHelper.lenghPassword(password)) {
            onLoginFailure(Config.PASSWORD_LENGH_ERROR)
        } else {
            database.setLisner(this)
            database.onLogin(user)
        }
    }

    override fun isLoggedIn(): Boolean {
        return database.user != null
    }

    private fun onLoginFailure(message: String) {
        view.onLoginFailure(message)
        view.onProcessEnd()
    }

    private fun startTaskActivity(nick: String) {
        val intent = Intent(context, TaskActivity::class.java)
        intent.putExtra(Config.NICKNAME_NODE, nick)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    override fun onSuccess() {
        view.onLoginSuccessful(Config.SUCCESS_LOGIN_MESSAGE)
        view.onProcessEnd()
        startTaskActivity(user.nickName)
    }

    override fun onFailure() {
        view.onLoginFailure(Config.PASSWORD_ERROR)
        view.onProcessEnd()
    }

    private fun setUser(name: String, password: String) {
        user.nickName = name
        user.password = password
    }

    private fun setContext(context: Context) {
        this.context = context
    }
}