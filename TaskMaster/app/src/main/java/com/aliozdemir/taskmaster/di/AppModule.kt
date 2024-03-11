package com.aliozdemir.taskmaster.di

import android.content.Context
import androidx.room.Room
import com.aliozdemir.taskmaster.data.datasource.TaskDataSource
import com.aliozdemir.taskmaster.data.repo.TaskRepository
import com.aliozdemir.taskmaster.room.Database
import com.aliozdemir.taskmaster.room.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository(taskDataSource: TaskDataSource): TaskRepository {
        return TaskRepository(taskDataSource)
    }

    @Provides
    @Singleton
    fun provideTaskDataSource(taskDao: TaskDao): TaskDataSource {
        return TaskDataSource(taskDao)
    }

    @Provides
    @Singleton
    fun provideKisilerDao(@ApplicationContext context: Context): TaskDao {
        val db = Room.databaseBuilder(context, Database::class.java, "taskmaster.sqlite")
            .createFromAsset("taskmaster.sqlite").build()

        return db.getTaskDao()
    }

}