package com.example.todolist;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

public class MyApplication extends Application {

    public static MyApplication instance;
    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "onCreate");
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "todo_list_db").
                allowMainThreadQueries().
                build();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
