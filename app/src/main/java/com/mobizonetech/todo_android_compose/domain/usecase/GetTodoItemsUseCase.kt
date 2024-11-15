package com.mobizonetech.todo_android_compose.domain.usecase

import com.mobizonetech.todo_android_compose.domain.model.TodoItem
import com.mobizonetech.todo_android_compose.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodoItemsUseCase @Inject constructor(private val repository: TodoRepository) {
    operator fun invoke(): List<TodoItem> = repository.getTodosItems()
}