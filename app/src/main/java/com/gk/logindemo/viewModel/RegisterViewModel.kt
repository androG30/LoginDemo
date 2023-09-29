package com.gk.logindemo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.logindemo.db.User
import com.gk.logindemo.db.UserRepository
import com.gk.rfidkotlin.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    val userNameEt = MutableLiveData<String>()
    val emailEt = MutableLiveData<String>()
    val courseEt = MutableLiveData<String>()
    val passwordEt = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>> get() = statusMessage

    fun registerUser() {
        if (userNameEt.value == null) {
            statusMessage.value = Event("Please enter username")
        } else if (emailEt.value == null) {
            statusMessage.value = Event("Please enter email")
        } else if (courseEt.value == null) {
            statusMessage.value = Event("Please enter course")
        } else if (passwordEt.value == null) {
            statusMessage.value = Event("Please enter password")
        } else {
            val username = userNameEt.value!!
            val email = emailEt.value!!
            val course = courseEt.value!!
            val password = passwordEt.value!!

            insert(User(0, username, email, course, password))
            userNameEt.value = ""
            emailEt.value = ""
            courseEt.value = ""
            passwordEt.value = ""
        }
    }

    private fun insert(registerUser: User) = viewModelScope.launch(Dispatchers.IO) {
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