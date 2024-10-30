package com.example.newapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    list: List<Post>,
    OnClick: (Int) -> Unit,
    onShareClick: (Int) -> Unit,
    onHeartClick: (Int) -> Unit,
    onMessageClick: (Int) -> Unit,
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        //rememberScrollState() -We can move the screen with this!
        LazyColumn() {
            items(items = list) { listOfPosts ->
                DynamicPost(
                    post = listOfPosts,
                    OnClick = { OnClick(it) },
                    onShareClick = { onShareClick(it) },
                    onHeartClick = { onHeartClick(it) },
                    onMessageClick = { onMessageClick(it) })
            }
        }
    }

}


//image function that displays our applications
@Composable
fun DynamicPost(
    post: Post,
    OnClick: (Int) -> Unit,
    onShareClick: (Int) -> Unit,
    onHeartClick: (Int) -> Unit,
    onMessageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(post.authorAvatar)
            Spacer(modifier = Modifier.width(8.dp))
            NickName(name = post.authorName)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Photo(photoRes = post.photo)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            HeartIcon(onHeartClick = { onHeartClick(post.id) }, post.isLiked)
            Spacer(modifier = Modifier.width(8.dp))
            MessageIcon(onMessageClick = { onMessageClick(post.id) })
            Spacer(modifier = Modifier.width(8.dp))
            ShareIcon(onShareClick = { onShareClick(post.id) })
            Spacer(modifier = Modifier.weight(1F))
            SaveIcon(onClick = { OnClick(post.id) }, post.bookmark)
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            NickName(name = post.authorName)
            Spacer(modifier = Modifier.width(8.dp))
            Description(text = post.description)
        }
    }
}

// interface of avatar
@Composable
fun Avatar(avatarRes: Int) {
    Image(
        painter = painterResource(id = avatarRes),
        contentDescription = "avatarInCircle",
        Modifier
            .padding(10.dp)
            .size(42.dp)
            .clip(CircleShape)
            .border(1.dp, color = Color.Black, shape = CircleShape)
    )
}

// we will add avatar here
@Composable
fun NickName(name: String) {
    Text(text = name)
}

// we will add photo here
@Composable
fun Photo(photoRes: Int) {
    Image(painter = painterResource(id = photoRes), contentDescription = "samplePic")
}


// interface of heart of icon
@Composable
fun HeartIcon(onHeartClick: () -> Unit, isLiked: Boolean) {

        Icon(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = "heart_icon",
            modifier = Modifier
                .size(46.dp)
                .paint(
                    if (isLiked) {
                        painterResource(id = R.drawable.red_heart)
                    } else {
                        painterResource(id = R.drawable.heart)
                    }
                )
                .clickable { onHeartClick() }
        )
    }


// interface of message of icon
@Composable
fun MessageIcon(onMessageClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(42.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.chat),
            contentDescription = "chat_icon",
            modifier = Modifier
                .clickable { onMessageClick() })
    }
}

// interface of share of icon
@Composable
fun ShareIcon(onShareClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(42.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.send),
            contentDescription = "send_icon",
            modifier = Modifier
                .clickable { onShareClick() })
    }
}

// interface of save of icon
@Composable
fun SaveIcon(onClick: () -> Unit, bookmark: Boolean) {

        Icon(
            painter = painterResource(id = R.drawable.save_instagram),
            contentDescription = "save_icon",
            modifier = Modifier
                .size(46.dp)
                .paint(
                    if (bookmark) {
                        painterResource(id = R.drawable.bookmark)
                    } else {
                        painterResource(id = R.drawable.save_instagram)
                    }
                )

                .clickable { onClick() }
        )

    }


// we will add description here
@Composable
fun Description(text: String) {
    Text(text = text)
}


