package com.example.newapplication.profilUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplication.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfilUserViewModel @Inject constructor(private val userService: UserService) : ViewModel() {

    //Retrieving data from UserService ApiResponse for a specific user
    var state: ProfileState by mutableStateOf(
        ProfileState(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        )
    )

    init {
        viewModelScope.launch {
            val userInfo = userService.getUser()




            state = state.copy(
                name = userInfo.results[0].name?.first ?: "",
                surname = userInfo.results[0].name?.last ?: "",
                email = userInfo.results[0].email ?: "",
                phone = userInfo.results[0].phone ?: "",
                gender = userInfo.results[0].gender ?: "",
                photo = userInfo.results[0].picture?.large ?: "",
                //birthday = userInfo.dob?.date ?: "znow pudło",
            )
        }
    }

}

// storing properties in ProfileState
data class ProfileState(
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val gender: String,
    val photo: String,
    val birthday: String,
)