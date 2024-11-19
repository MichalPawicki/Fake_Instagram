package com.example.newapplication


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplication.sharedPref.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userService: UserService,
    private val sharedPref: SharedPref //SharedPref Injection

) : ViewModel() {

    var state: State by mutableStateOf(State())

    init {

        val hasFetched = sharedPref.isFetched()
        if (!hasFetched) {
            // Mark data as fetched
            sharedPref.markAsFetched()
        }
        state = state.copy(list = listOfPosts.values.toList())
        viewModelScope.launch {
            println(userService.getUser())
            println(userService.getUsers(10))
        }
    }

    fun onShareIconClick(postId: Int) {

        println("SHARE ICON VIEWMODEL: $postId")
    }

    fun onSaveIconClick(postId: Int) {

        val idOfListOfPosts = listOfPosts[postId]

        if (idOfListOfPosts != null) {
            val modify = idOfListOfPosts.copy(bookmark = !idOfListOfPosts.bookmark)
            listOfPosts[postId] = modify
        }
        state = state.copy(list = listOfPosts.values.toList())

        println("SAVE ICON VIEWMODEL: $postId")
    }

    fun onHeartIconClick(postId: Int) {

        val idOfListOfPosts = listOfPosts[postId]

        if (idOfListOfPosts != null) {
            val modify = idOfListOfPosts.copy(isLiked = !idOfListOfPosts.isLiked)
            listOfPosts[postId] = modify
        }
        state = state.copy(list = listOfPosts.values.toList())


        println("HEART ICON VIEWMODEL")
    }

    fun onMessageIconClick(postId: Int) {
        println("MESSAGE ICON VIEWMODEL")
    }

}


// created a class, which will store a data (dynamic components)
data class Post(
    val id: Int = 0,
    val authorName: String = "",
    val authorAvatar: Int = 0,
    val description: String = "",
    val photo: Int = 0,
    val isLiked: Boolean = false,
    val bookmark: Boolean = false
)


data class State(val list: List<Post> = mutableListOf())


private val listOfPosts = mutableMapOf(
    1 to Post(
        id = 1,
        authorAvatar = R.drawable.avatarpic,
        authorName = "MisiaPysia",
        photo = R.drawable.sample,
        description = "Good vibes!"
    ), 2 to Post(
        id = 2,
        authorAvatar = R.drawable.woman,
        authorName = "CoolCat",
        photo = R.drawable.street,
        description = "My favourite shop!"
    ), 3 to Post(
        id = 3,
        authorAvatar = R.drawable.woman2,
        authorName = "SportWoman!",
        photo = R.drawable.woman_running,
        description = "time to go :)!"
    ), 4 to Post(
        id = 4,
        authorAvatar = R.drawable.photographer,
        authorName = "BirdHunter",
        photo = R.drawable.bird,
        description = "my personal model :)"
    )
)