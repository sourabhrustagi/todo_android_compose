package com.mobizonetech.todo_android_compose.domain.model

data class TodoItem(
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false
)