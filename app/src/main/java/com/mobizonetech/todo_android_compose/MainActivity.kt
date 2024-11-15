package com.mobizonetech.todo_android_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobizonetech.todo_android_compose.domain.model.TodoItem
import com.mobizonetech.todo_android_compose.presentation.TodoViewModel
import com.mobizonetech.todo_android_compose.ui.theme.Todo_android_composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Todo_android_composeTheme {
                TodoScreen()
            }
        }
    }
}

@Composable
fun TodoScreen(viewModel: TodoViewModel = hiltViewModel()) {
    val todoItems by viewModel.todos.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        var newItemTitle by remember { mutableStateOf(TextFieldValue("")) }

        BasicTextField(
            value = newItemTitle,
            onValueChange = { newItemTitle = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(8.dp)
                ) {
                    if (newItemTitle.text.isEmpty()) {
                        Text("Add a new to-do")
                    }
                    innerTextField()
                }
            }
        )
        Button(
            onClick = {
                if (newItemTitle.text.isNotBlank()) {
                    viewModel.addTodoItem(newItemTitle.text)
                    newItemTitle = TextFieldValue("")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(todoItems) { item ->
                TodoItemRow(
                    item = item,
                    onDelete = { viewModel.deleteTodo(item) },
                    onToggleComplete = { viewModel.toggleTodoItemCompletion(item) }
                )
            }
        }
    }
}

@Composable
fun TodoItemRow(
    item: TodoItem,
    onDelete: () -> Unit,
    onToggleComplete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = item.isCompleted,
                onCheckedChange = { onToggleComplete() }
            )
            Text(
                text = item.title,
                style = if (item.isCompleted) MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.LineThrough
                ) else MaterialTheme.typography.bodyMedium
            )
        }
        IconButton(onClick = { onDelete() }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }

    }
}