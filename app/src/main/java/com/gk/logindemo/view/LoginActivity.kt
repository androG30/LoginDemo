package com.gk.logindemo.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gk.logindemo.R
import com.gk.logindemo.databinding.ActivityLoginBinding
import com.gk.logindemo.db.UserDatabase
import com.gk.logindemo.db.UserRepository
import com.gk.logindemo.viewModel.LoginViewModel
import com.gk.logindemo.viewModel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = LoginViewModelFactory(repository)

        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        binding.loginModel = loginViewModel
        binding.lifecycleOwner = this

        loginViewModel.message.observe(this) { message ->
            message.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}