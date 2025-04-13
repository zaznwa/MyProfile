package com.geeks.myprofile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.SemanticsProperties.EditableText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(padding: PaddingValues) {
    var name by remember { mutableStateOf("Имя") }
    var surname by remember { mutableStateOf("Фамилия") }
    var description by remember { mutableStateOf("Описание") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ProfileImage()

        Spacer(modifier = Modifier.height(16.dp))

        EditableText(label = "Имя", text = name) { name = it }
        EditableText(label = "Фамилия", text = surname) { surname = it }
        EditableText(label = "Описание", text = description) { description = it }
    }
}
@Composable
fun ProfileImage() {
    AsyncImage(
        model = "https://instagram.ffru7-1.fna.fbcdn.net/v/t51.2885-19/436588015_1633654037371594_5504914056324962514_n.jpg?stp=dst-jpg_s150x150_tt6&_nc_ht=instagram.ffru7-1.fna.fbcdn.net&_nc_cat=110&_nc_oc=Q6cZ2QEbf0lW-On73gADtOOhJQCrvkde6zGtxGpMYD37klHd0AeZiNE9hkzN7dJJZHGFW8c&_nc_ohc=FqdUhTDpryUQ7kNvwGlhEJl&_nc_gid=LDd9PP3ix7AQVZv4eSva_Q&edm=AP4sbd4BAAAA&ccb=7-5&oh=00_AfEvaQIbfixFF8nu9u7yRNDOlPqYn1IokG1SvrYpuJmd9A&oe=680195A3&_nc_sid=7a9f4b",
        contentDescription = "Аватар",
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun EditableText(label: String, text: String, onTextChange: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { showDialog = true },
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        if (showDialog) {
            EditDialog(
                label = label,
                initialText = text,
                onDismiss = { showDialog = false },
                onConfirm = {
                    onTextChange(it)
                    showDialog = false
                }
            )
        }
    }
}
@Composable
fun EditDialog(
    label: String,
    initialText: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var value by remember { mutableStateOf(initialText) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Изменить $label") },
        text = {
            TextField(
                value = value,
                onValueChange = { value = it },
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(value) }) {
                Text("Сохранить")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}