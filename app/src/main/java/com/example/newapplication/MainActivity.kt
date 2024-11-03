package com.example.newapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newapplication.contacts.Contacts
import com.example.newapplication.profilUser.ProfilUser
import com.example.newapplication.profile.Profile
import com.example.newapplication.ui.theme.NewApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable


@Serializable
data class Profile(val name: String)


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NewApplicationTheme {
                Scaffold(content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") {
                                Home(
                                    list = homeViewModel.state.list,
                                    OnClick = { homeViewModel.onSaveIconClick(it) },
                                    onShareClick = { homeViewModel.onShareIconClick(it) },
                                    onHeartClick = { homeViewModel.onHeartIconClick(it) },
                                    onMessageClick = { homeViewModel.onMessageIconClick(it) }
                                )
                            }
                            composable("profile") { Profile() }
                            composable<Profile> { ProfilUser() }
                            composable("contacts") { Contacts({ navController.navigate(Profile("user")) }) }

                        }
                    }
                },

                    // the appearance of the bottom application bar

                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier
                                .background(Color.Red)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "home_icon",
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clickable { navController.navigate("home") }
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "search_icon",
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clickable { navController.navigate("contacts") }
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.add),
                                    contentDescription = "add_icon",
                                    modifier = Modifier
                                        .size(46.dp)
                                        .clickable { }
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.user_avatar),
                                    contentDescription = "user_avatar",
                                    modifier = Modifier
                                        .size(54.dp)
                                        .clickable { navController.navigate("profile") },
                                )
                            }

                        }
                    },
                    topBar = {
                        TopAppBar(title = {
                            Text(text = stringResource(id = R.string.home_page))
                        })
                    })

            }
        }
    }

}