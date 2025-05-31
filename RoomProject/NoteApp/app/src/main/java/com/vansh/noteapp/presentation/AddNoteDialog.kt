package com.vansh.noteapp.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.vansh.noteapp.model.NoteState
import com.vansh.noteapp.model.data.NoteEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    BasicAlertDialog(
        modifier = Modifier
            .height(370.dp)
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onPrimary),
        onDismissRequest = {
            onEvent(NoteEvent.HideDialog)
        },
        properties = DialogProperties(dismissOnClickOutside = false),
        content = {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = "Add Note",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    value = state.title,
                    onValueChange = {
                        onEvent(NoteEvent.SetTitle(it))
                    },
                    label = {
                        Text(
                            text = "Enter Title"
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                BasicTextField(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp)),
                    value = state.content,
                    onValueChange = {
                            onEvent(NoteEvent.SetContent(it))
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Start
                    ),
                    decorationBox = {
                        innerTextField ->
                        if(state.content.isEmpty()){
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Enter Content",
                                fontSize = 20.sp,
                                color = Color.Gray
                            )
                        }else innerTextField()
                    },
                    cursorBrush = SolidColor(Color.Black),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onEvent(NoteEvent.SaveNote)
                        }
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    modifier = Modifier.padding(start = 90.dp),
                    onClick = {
                        onEvent(NoteEvent.SaveNote)
                    }
                ) {
                    Text(
                        text = "Save",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    )
}