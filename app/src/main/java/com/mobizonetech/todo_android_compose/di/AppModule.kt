package com.mobizonetech.todo_android_compose.di

import com.mobizonetech.todo_android_compose.data.repository.TodoRepositoryImpl
import com.mobizonetech.todo_android_compose.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoRepository(): TodoRepository = TodoRepositoryImpl()
}