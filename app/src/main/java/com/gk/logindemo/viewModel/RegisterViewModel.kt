package com.gk.logindemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.logindemo.db.RegisterUser
import com.gk.logindemo.db.RegisterUserRepository
import com.gk.rfidkotlin.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val repository: RegisterUserRepository) : ViewModel() {

    val users = repository.users

    private lateinit var registerUser: RegisterUser

     val userName = MutableLiveData<String>()
     val email = MutableLiveData<String>()
     val course = MutableLiveData<String>()
     val password = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>> get() = statusMessage

    fun registerUser() {
        if (userName.value == null) {
            statusMessage.value = Event("Please enter username")
        } else if (email.value == null) {
            statusMessage.value = Event("Please enter email")
        } else if (course.value == null) {
            statusMessage.value = Event("Please enter course")
        } else if (password.value == null) {
            statusMessage.value = Event("Please enter password")
        } else {
            registerUser.username = userName.value!!
            registerUser.email = email.value!!
            registerUser.course = course.value!!
            registerUser.password = password.value!!

            insert(registerUser)
        }
    }

    private fun insert(registerUser: RegisterUser) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(registerUser)

        withContext(Dispatchers.Main) {
            if (newRowId > -1) {
                statusMessage.value = Event("User Inserted Successfully! $newRowId")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

}