package com.vansh.noteapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vansh.noteapp.model.Note
import com.vansh.noteapp.model.NoteState
import com.vansh.noteapp.model.SortType
import com.vansh.noteapp.model.data.NoteEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                title = {
                    Text(
                        text = "Note App",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                        Text(
                            modifier = Modifier
                                .clickable {
                                    onEvent(NoteEvent.SortNote(sortType = SortType.ASC))
                                }
                                .padding(horizontal = 10.dp),
                            text = "ASC",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .clickable {
                                    onEvent(NoteEvent.SortNote(sortType = SortType.DESC))
                                }
                                .padding(horizontal = 10.dp),
                            text = "DESC",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(NoteEvent.ShowDialog)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    ) {
        if (state.isAddingNote) AddNoteDialog(state,onEvent)
        LazyColumn(
            modifier = Modifier
                .padding(it),
        ) {
            items(state.notes) { note ->
                if (state.notes.isEmpty()){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Empty!",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }else NoteItem(note,onEvent)
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onEvent: (NoteEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Green)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = note.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .size(35.dp),
                onClick = {
                    onEvent(NoteEvent.DeleteNote(note = note))
                }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete"
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(10.dp),
            text = note.content,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
