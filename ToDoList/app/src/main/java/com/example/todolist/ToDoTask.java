package com.example.todolist;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = AppDatabase.TABLE_NAME_TODO)
public class ToDoTask implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String status;
    public String category;
    public String duration;

    public ToDoTask() {
    }
}
