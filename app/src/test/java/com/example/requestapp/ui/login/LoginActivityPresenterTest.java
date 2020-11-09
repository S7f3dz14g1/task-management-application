package com.example.requestapp.ui.login;

import android.content.Context;

import com.example.requestapp.Utils.Config;
import com.example.requestapp.database.Database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginActivityPresenterTest {

    @Mock
    private LoginActivityContract.View view;
    private LoginActivityPresenter presenter;

    @Before
    public void setup() {
        view = mock(LoginActivityContract.View.class);
        presenter = new LoginActivityPresenter(view);
        presenter.setDatabase(Mockito.mock(Database.class));

    }
    /*
    sprawdzić czy jest podłączenie z internetem
    sprawdzenie czy nie jest dobry nick a złe hasło
    sprawdzić czy jest poprawnie loguje się
    sprawdzić czy stworzyło nowe konto
     */
    @Test
    public void onLoginClicked_nickAndPasswordAreEmpty_onLoginFailure() {
        //given
        String nick = "";
        String password = "";
        //when
        presenter.onLoginClicked(mock(Context.class),nick,password);
        //then
        verify(view).onLoginFailure(Config.EMPTY_LOGIN_DATE_ERROR);
    }
    @Test
    public void onLoginClicked_onlyPasswordIsEmpty_onLoginFailure() {
        //given
        String nick = "nick";
        String password = "";
        //when
        presenter.onLoginClicked(mock(Context.class),nick,password);
        //then
        verify(view).onLoginFailure(Config.EMPTY_LOGIN_DATE_ERROR);
    }
    @Test
    public void onLoginClicked_onlyNickIsEmpty_onLoginFailure() {
        //given
        String nick = "";
        String password = "password";
        //when
        presenter.onLoginClicked(mock(Context.class),nick,password);
        //then
        verify(view).onLoginFailure(Config.EMPTY_LOGIN_DATE_ERROR);
    }
    @Test
    public void onLoginClicked_passwordLenghLessThanFive_onLoginFailure() {
        //given
        String nick = "Szfedek";
        String password = "123456";
        //when
        presenter.onLoginClicked(mock(Context.class),nick,password);
        //then
        verify(view).onLoginFailure(Config.SUCCESS_LOGIN_MESSAGE);
    }


}