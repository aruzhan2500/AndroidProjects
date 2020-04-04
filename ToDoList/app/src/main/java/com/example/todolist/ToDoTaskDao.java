package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoTaskDao {
    @Query("SELECT * FROM todotask")
    List<ToDoTask> getAll();

    @Query("SELECT * FROM todotask WHERE id = :id")
    ToDoTask getToDoTask(long id);

    @Insert
    void insert(ToDoTask todotask);

    @Query("Update todotask Set title = :title, description = :description, status = :status, category = :category, duration = :duration where id = :id")
    void update(long id, String title, String description, String status, long category, String duration);

    @Query("Delete From todotask where id = :id")
    void delete(long id);
}
