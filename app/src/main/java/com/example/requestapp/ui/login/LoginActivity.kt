package com.example.requestapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.requestapp.R
import com.example.requestapp.ui.fragment.TaskActivity

class LoginActivity : AppCompatActivity(), LoginActivityContract.View {

    private var nick: TextView? = null
    private var password: TextView? = null
    private var login: Button? = null
    private var progressBar: ProgressBar? = null

    private var presenter: LoginActivityContract.Presenter = LoginActivityPresenter(this, this)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (presenter.isLoggedIn()) {
            finish()
            startActivity(Intent(this, TaskActivity::class.java))
        }
        setContentView(R.layout.activity_login)
        initComponents()
        progressBar!!.visibility = View.INVISIBLE
        login!!.setOnClickListener(onClickListener)
    }

    private fun initComponents() {
        nick= findViewById<EditText>(R.id.editTextNickName)
        password = findViewById<EditText>(R.id.editTextPassword)
        login = findViewById(R.id.buttonLogin)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onLoginSuccessful(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onLoginFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onProcessStart() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun onProcessEnd() {
        progressBar!!.visibility = View.INVISIBLE
    }

    private val onClickListener = View.OnClickListener {
        presenter.onLoginClicked(this,
                nick!!.text.toString(),
                password!!.getText().toString())
    }

}