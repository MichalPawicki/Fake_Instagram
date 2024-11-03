package com.example.newapplication.contacts

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Text as Text


//downloading contact data from Contact ViewModel, creating searchBar and creating contact list
@SuppressLint("UnrememberedMutableState")
@Composable
fun Contacts(name: () -> Unit) {
    val viewModel = hiltViewModel<ContactViewModel>()

    var text by remember { mutableStateOf("") }


    // SearchBar and LazyColumn contacts
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .padding(bottom = 10.dp)
            .clip(shape = RoundedCornerShape(40.dp)),
            value = text,
            onValueChange = {
                viewModel.filterContact(text = it)
                text = it
            },
            placeholder = { Text(text = "Input Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) })
        Column(modifier = Modifier.fillMaxSize()) {
            ListOfContacts(viewModel.state.list) { name() }
        }

    }

}

// inserting a single contact into the contact list
@Composable
fun ListOfContacts(contacts: List<Contact>, name: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(contacts) { contact ->
            ContactItem(contact, {name()})
        }
    }
}

//single contact appearance
@Composable
fun ContactItem(contact: Contact, name: () -> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { name() },
        colors = CardDefaults.cardColors(Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = contact.name)
            Text(text = contact.phone)
        }
    }
}


