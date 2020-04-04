package com.example.todolist;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Category.class,
            parentColumns = {"id"},
            childColumns = {"category"},
            onDelete = CASCADE)},
        indices = {@Index("category")})
public class ToDoTask {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String title;
    public String description;
    public String status;
    public long category;
    public String duration;

}
