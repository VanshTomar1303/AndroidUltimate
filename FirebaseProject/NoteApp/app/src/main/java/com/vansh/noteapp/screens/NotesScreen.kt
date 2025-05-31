package com.vansh.noteapp.screens

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.vansh.noteapp.events.NoteEvent
import com.vansh.noteapp.events.UserEvent
import com.vansh.noteapp.model.Note
import com.vansh.noteapp.model.NoteState
import com.vansh.noteapp.navigation.NoteNavigationItem
import com.vansh.noteapp.ui.theme.colorBlack
import com.vansh.noteapp.ui.theme.colorLightBlack
import com.vansh.noteapp.ui.theme.colorRed
import com.vansh.noteapp.ui.theme.colorWhite

@Composable
fun NoteScreen(
    navHostController: NavHostController,
    state: NoteState,
    authEvent: (UserEvent) -> Unit,
    event: (NoteEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.error) {
        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape),
                onClick = {
                    navHostController.navigate(NoteNavigationItem.InsertNoteScreen.route+"/defaultId")
                },
                contentColor = colorWhite,
                containerColor = colorRed
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add notes"
                )
            }
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorBlack)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Notes",
                        color = colorWhite,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(
                        onClick = {
                            authEvent(UserEvent.SignOut)
                        }
                    ) {
                        Text(
                            text = "Sign-Out",
                            color = colorWhite,
                            fontSize = 18.sp
                        )
                    }
                }
                if (!state.dataLoading) {
                    LazyColumn {
                        items(state.notes) { item ->
                            NoteItem(item, event, navHostController)
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    item: Note,
    event: (NoteEvent) -> Unit,
    navHostController: NavHostController
) {

    var expend by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(colorLightBlack)
    ){

        val context = LocalContext.current
        DropdownMenu(
            expanded = expend,
            onDismissRequest = {
                expend = !expend
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(colorWhite),
            properties = PopupProperties(clippingEnabled = true),
            offset = DpOffset(x = (-40).dp, y = 0.dp)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Update",
                        color = colorBlack
                    )
                },
                onClick = {
                    navHostController.navigate(NoteNavigationItem.InsertNoteScreen.route+"/${item.id}")
                    event(NoteEvent.UpdateNoteDataLoading(item.id))
                    expend = !expend
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Delete",
                        color = colorBlack
                    )
                },
                onClick = {
                    val alertDialog = AlertDialog.Builder(context)
                    alertDialog.setMessage("You want to delete this note?")
                    alertDialog.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            event(NoteEvent.DeleteNote(item.id))
                            p0?.dismiss()
                            expend = !expend
                        }
                    })
                    alertDialog.setNegativeButton("No",object : DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0?.dismiss()
                            expend = !expend
                        }
                    })
                    alertDialog.show()
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Share",
                        color = colorBlack
                    )
                },
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).also {
                        it.type = "text/plain"
                        it.putExtra(Intent.EXTRA_TITLE,item.title)
                        it.putExtra(Intent.EXTRA_TEXT,item.description)
                    }
                    val chooser = Intent.createChooser(intent, "Share via")
                    if(intent.resolveActivity(context.packageManager) != null){
                        context.startActivity(chooser)
                    }else{
                        Toast.makeText(
                            context,
                            "It cant be open in any app's",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    expend = !expend
                }
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "Menu",
            tint = colorWhite,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .clickable {
                    expend = true
                }
        )
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = item.title,
                style = TextStyle(color = colorWhite),
            )
            Text(
                text = item.description,
                style = TextStyle(color = Color.LightGray)
            )
        }
    }
}

