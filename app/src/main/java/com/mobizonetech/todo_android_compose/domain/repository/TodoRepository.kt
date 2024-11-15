package com.mobizonetech.todo_android_compose.domain.repository

import com.mobizonetech.todo_android_compose.domain.model.TodoItem

interface TodoRepository {
    fun getTodosItems(): List<TodoItem>
    fun addTodoItem(todoItem: TodoItem)
    fun deleteTodoItem(todoItem: TodoItem)
    fun toggleCompletion(todoItem: TodoItem)
}