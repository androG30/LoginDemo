package com.gk.logindemo.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gk.logindemo.R
import com.gk.logindemo.databinding.ActivityRegisterBinding
import com.gk.logindemo.db.RegisterUserDatabase
import com.gk.logindemo.db.RegisterUserRepository
import com.gk.logindemo.viewModel.RegisterViewModel
import com.gk.logindemo.viewModel.RegisterViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val dao = RegisterUserDatabase.getInstance(application).registerUserDAO
        val repository = RegisterUserRepository(dao)
        val factory = RegisterViewModelFactory(repository)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
        binding.registerModel = registerViewModel
        binding.lifecycleOwner = this

        registerViewModel.message.observe(this) { message ->
            message.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}