package com.aritra.notifykmm.android.noteDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    noteId: Long,
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val hasNoteBeenSaved by viewModel.noteBeenSaved.collectAsState()

    val navigateBack: () -> Unit = remember {
        {
            navController.popBackStack()
        }
    }

    LaunchedEffect(key1 = hasNoteBeenSaved) {
        if (hasNoteBeenSaved) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack,
                        content = {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Save")
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = viewModel::saveNote,
                        content = {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
                        }
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            TransparentTextField(
                text = state.title,
                hint = "Title",
                isHintVisible = state.isTitleFocused,
                onValueChanged = viewModel::noteTitleChanged,
                onFocusChanged = {
                    viewModel.noteTitleFocusedChanged(it.isFocused)
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentTextField(
                text = state.description,
                hint = "Description",
                isHintVisible = state.isDescriptionFocused,
                onValueChanged = viewModel::noteDescriptionChanged,
                onFocusChanged = {
                    viewModel.noteDescriptionFocusedChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 12.sp),
                modifier = Modifier.weight(1f)
            )
        }
    }

}