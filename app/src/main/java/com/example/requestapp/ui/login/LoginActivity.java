package com.example.requestapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.requestapp.R;
import com.example.requestapp.ui.fragment.TaskActivity;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {

    private TextView nick, password;
    private Button login;
    private ProgressBar progressBar;

    private LoginActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
        if (presenter.isLoggedIn()) {
            startActivity(new Intent(this, TaskActivity.class));
        }
        setContentView(R.layout.activity_login);
        initComponents();
        progressBar.setVisibility(View.INVISIBLE);
        login.setOnClickListener(onClickListener);
    }

    private void setPresenter() {
        presenter = new LoginActivityPresenter(this);
    }

    private void initComponents() {
        nick = findViewById(R.id.editTextNickName);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onLoginSuccessful(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProcessStart() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProcessEnd() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onLoginClicked(LoginActivity.this, nick.getText().toString(), password.getText().toString());
        }
    };
}