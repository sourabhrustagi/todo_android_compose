package com.mobizonetech.todo_android_compose.data.repository

import com.mobizonetech.todo_android_compose.domain.model.TodoItem
import com.mobizonetech.todo_android_compose.domain.repository.TodoRepository

class TodoRepositoryImpl : TodoRepository {
    private val todoItems = mutableListOf<TodoItem>()

    override fun getTodosItems(): List<TodoItem> = todoItems

    override fun addTodoItem(todoItem: TodoItem) {
        todoItems.add(todoItem)
    }

    override fun deleteTodoItem(todoItem: TodoItem) {
        todoItems.remove(todoItem)
    }

    override fun toggleCompletion(todoItem: TodoItem) {
        val index = todoItems.indexOfFirst { it.id == todoItem.id }
        if (index != -1) {
            val updatedItem = todoItems[index].copy(isCompleted = !todoItems[index].isCompleted)
            todoItems[index] = updatedItem
        }
    }
}