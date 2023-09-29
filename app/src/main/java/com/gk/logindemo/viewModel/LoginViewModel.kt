package com.gk.logindemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.logindemo.db.UserRepository
import com.gk.rfidkotlin.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userNameEt = MutableLiveData<String>()
    val passwordEt = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>> get() = statusMessage

    fun loginUser() {
        if (userNameEt.value == null) {
            statusMessage.value = Event("Please enter username")
        } else if (passwordEt.value == null) {
            statusMessage.value = Event("Please enter password")
        } else {
            val username = userNameEt.value!!
            val password = passwordEt.value!!

            viewModelScope.launch(Dispatchers.IO) {
                val user = userRepository.getUserFromUserName(username)

                withContext(Dispatchers.Main){
                    if (user == null) {
                        statusMessage.value = Event("User not register")
                    } else {
                        if (user.password == password) {
                            statusMessage.value = Event("Login Successful")
                        } else {
                            statusMessage.value = Event("Entered password is wrong")
                        }
                    }
                }
            }


            userNameEt.value = ""
            passwordEt.value = ""
        }
    }

}