package com.example.newapplication.contacts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplication.UserService
import com.example.newapplication.apiFiles.Results
import com.example.newapplication.apiFiles.UserServiceApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val userService: UserService) : ViewModel() {

    //downloading contact data, creating a contact list and filtering the contact list

    private lateinit var listOfUsers: UserServiceApiResponse

    var state: ContactState by mutableStateOf(ContactState())

    init {
        viewModelScope.launch {

            listOfUsers = userService.getUsers(20)
            state = state.copy(mapContacts(listOfUsers.results))

        }
    }

    private fun mapContacts(users: List<Results>): List<Contact> {
        val list =
            users.map {
                Contact(
                    it.name?.first ?: "",
                    it.phone ?: ""
                )
            }
        return list
    }

    fun filterContact(text: String = "") {
        val filteredContacts = listOfUsers.results.filter { user ->
            user.name?.first?.contains(text, ignoreCase = true) == true ||
                    user.phone?.contains(text, ignoreCase = true) == true
        }
        state = state.copy(mapContacts(filteredContacts))
    }

}

data class ContactState(val list: List<Contact> = listOf())

data class Contact(val name: String, val phone: String)