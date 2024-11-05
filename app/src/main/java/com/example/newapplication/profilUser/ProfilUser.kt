package com.example.newapplication.profilUser


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfilUser() {
    val viewModel = hiltViewModel<ProfilUserViewModel>()


    //the appearance of a given user's profile (similar like Profile.kt)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Test comment
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .rotate(45F)
                .clip(RoundedCornerShape(32.dp))
                .size(140.dp)
                .border(3.dp, Color.Black)
                .padding(5.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = viewModel.state.photo),
                contentDescription = "avatar photo",
                modifier = Modifier
                    .fillMaxSize(1F)
                    .rotate(-45F),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = viewModel.state.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = viewModel.state.surname,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp),

                text = "Gender:", fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 15.dp),
                text = viewModel.state.gender
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp),

                text = "Birthday:", fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 15.dp),
                text = viewModel.state.birthday
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp),

                text = "Phone:", fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 15.dp),
                text = viewModel.state.phone
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 5.dp),
                text = "Email:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Start)
                    .padding(bottom = 50.dp),
                text = viewModel.state.email
            )
        }

    }
}









