package com.aritra.notifykmm.android.noteList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aritra.notifykmm.domain.note.Note
import com.aritra.notifykmm.domain.time.DateTimeUtils

@Composable
fun NoteItem(
    note: Note,
    onNoteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val date = remember(note.createdAt) {
        DateTimeUtils.formatAsDate(note.createdAt)
    }

    OutlinedCard(
        border = CardDefaults.outlinedCardBorder().copy(0.dp),
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight()
            .clickable { onNoteClick() }
            .clip(RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp)

    ) {
        Box(
            modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier.fillMaxWidth()
            ) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Spacer(modifier = modifier.height(14.dp))

                Text(
                    text = note.description,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(14.dp))

                Text(
                    text = date,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}