package com.vansh.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.vansh.noteapp.events.NoteEvent
import com.vansh.noteapp.model.Note
import com.vansh.noteapp.model.NoteState
import com.vansh.noteapp.ui.theme.colorBlack
import com.vansh.noteapp.ui.theme.colorLightBlack
import com.vansh.noteapp.ui.theme.colorRed
import com.vansh.noteapp.ui.theme.colorWhite

@Composable
fun InsertNoteScreen(
    navController: NavHostController,
    id1: String?,
    state: NoteState,
    event: (NoteEvent) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(state.noteSaved) {
        if (state.noteSaved) {
            Toast.makeText(context, "Note Created!", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }

    LaunchedEffect(state.noteUpdated) {
        if (state.noteUpdated) {
            Toast.makeText(context, "Note Updated!", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape),
                onClick = {
                    if (state.title.isEmpty() && state.description.isEmpty()){
                        Toast
                            .makeText(context,"Empty Values!", Toast.LENGTH_LONG)
                            .show()
                    }else{
                        if(id1 != "defaultId"){
                            event(NoteEvent.UpdateNote(id1.toString()))
                        }else{
                            event(NoteEvent.SaveNote)
                        }
                    }
                },
                contentColor = colorWhite,
                containerColor = colorRed
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) { innerPadding ->
        Box(
           modifier = Modifier.padding(innerPadding)
               .fillMaxSize()
               .background(colorBlack)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Insert Notes",
                    color = colorWhite,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = state.title,
                    onValueChange = {
                        event(NoteEvent.SetTitle(it))
                    },
                    label = {
                        Text(
                            text = "Title",
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorWhite,
                        unfocusedTextColor = colorWhite,
                        focusedContainerColor = colorLightBlack,
                        unfocusedContainerColor = colorLightBlack,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = state.description,
                    onValueChange = {
                        event(NoteEvent.SetDescription(it))
                    },
                    label = {
                        Text(
                            text = "Enter description",
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorWhite,
                        unfocusedTextColor = colorWhite,
                        focusedContainerColor = colorLightBlack,
                        unfocusedContainerColor = colorLightBlack,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxHeight(0.6f)
                )
            }
        }
    }
}