package com.mobizonetech.todo_android_compose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobizonetech.todo_android_compose.domain.model.TodoItem
import com.mobizonetech.todo_android_compose.domain.usecase.AddTodoItemUseCase
import com.mobizonetech.todo_android_compose.domain.usecase.DeleteTodoItemUseCase
import com.mobizonetech.todo_android_compose.domain.usecase.GetTodoItemsUseCase
import com.mobizonetech.todo_android_compose.domain.usecase.ToggleTodoItemCompletionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodoItemUseCase: GetTodoItemsUseCase,
    private val addTodoItemUseCase: AddTodoItemUseCase,
    private val deleteTodoItemUseCase: DeleteTodoItemUseCase,
    private val toggleTodoItemCompletionUseCase: ToggleTodoItemCompletionUseCase
) : ViewModel() {
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    init {
        loadTodoItems()
    }

    private fun loadTodoItems() {
        viewModelScope.launch {
            _todos.value = getTodoItemUseCase()
        }
    }

    fun addTodoItem(title: String) {
        viewModelScope.launch {
            val newItem = TodoItem(title = title)
            addTodoItemUseCase(newItem)
            loadTodoItems()
        }
    }

    fun deleteTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            deleteTodoItemUseCase(todoItem)
            loadTodoItems()
        }
    }

    fun toggleTodoItemCompletion(todoItem: TodoItem) {
        toggleTodoItemCompletionUseCase(todoItem)
        loadTodoItems()
    }
}