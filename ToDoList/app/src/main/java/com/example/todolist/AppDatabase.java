package com.example.todolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDoTask.class, Category.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "app_db";
    public static final String TABLE_NAME_TODO = "todo";

    public abstract ToDoTaskDao toDoTaskDao();
    public abstract CategoryDao categoryDao();
}
