package com.aritra.notifykmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amsavarthan.note.android.theme.NotifyKMMTheme
import com.aritra.notifykmm.android.noteList.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotifyKMMTheme {
                NoteListScreen()
            }
        }
    }
}