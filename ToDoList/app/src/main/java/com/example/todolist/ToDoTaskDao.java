package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoTaskDao {
    @Query("SELECT * FROM toDoTasks")
    List<ToDoTask> getAll();

    @Query("SELECT * FROM toDoTasks WHERE id = :id")
    ToDoTask getToDoTaskById(int id);

    @Insert
    long insertToDoTask(ToDoTask todoTask);

    @Update
    int updateToDoTask(ToDoTask todoTask);

    @Delete
    int deleteToDoTask(ToDoTask todoTask);
}
