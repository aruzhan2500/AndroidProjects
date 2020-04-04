package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoTaskDao {
    @Query("SELECT * FROM " + AppDatabase.TABLE_NAME_TODO)
    List<ToDoTask> getAll();

    @Query("SELECT * FROM " + AppDatabase.TABLE_NAME_TODO + " WHERE category = :category")
    List<ToDoTask> getToDoListByCategory(String category);

    @Query("SELECT * FROM " + AppDatabase.TABLE_NAME_TODO + " WHERE id = :id")
    ToDoTask getToDoListById(int id);

    @Insert
    long insertToDoTask(ToDoTask todoTask);

    @Update
    int updateToDoTask(ToDoTask todoTask);

    @Delete
    int deleteToDoTask(ToDoTask todoTask);
}
