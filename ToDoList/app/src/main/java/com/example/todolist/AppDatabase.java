package com.example.todolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDoTask.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ToDoTaskDao toDoTaskDao();
    public abstract CategoryDao categoryDao();
}
