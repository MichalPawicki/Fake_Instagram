package com.example.newapplication.profilUser

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.newapplication.Profile
import com.example.newapplication.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfilUserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val profile = savedStateHandle.toRoute<Profile>()


    //Retrieving data from  for a specific user
    var state: ProfileState by mutableStateOf(
        ProfileState(
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

            state = state.copy(
                name = profile.name,
                surname =  "",
                email = "",
                phone = "",
                gender =  "",
                photo =  "",
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
)