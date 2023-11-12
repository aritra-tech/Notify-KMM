package com.aritra.notifykmm.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aritra.notifykmm.android.noteDetail.NoteDetailsScreen
import com.aritra.notifykmm.android.noteList.NoteListScreen

@Composable
fun NotifyKMMApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "note_list"
    ) {

        composable(route = "note_list") {
            NoteListScreen(navController = navController)
        }

        composable(
            route = "note_detail/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { navBackStackEntry ->
            val noteId = navBackStackEntry.arguments?.getLong("noteId") ?: -1L
            NoteDetailsScreen(noteId = noteId, navController = navController)
        }
    }
}