package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener{

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    FloatingActionButton floatingActionButton;
    MyApplication myApplication = MyApplication.getInstance();

    public static final int NEW_TODO_REQUEST_CODE = 200;
    public static final int UPDATE_TODO_REQUEST_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        checkIfAppLaunchedFirstTime();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ToDoDetailActivity.class), NEW_TODO_REQUEST_CODE);
            }
        });
    }

    @Override
    public void launchIntent(int id) {
        startActivityForResult(new Intent(MainActivity.this, ToDoDetailActivity.class).putExtra("id", id), UPDATE_TODO_REQUEST_CODE);
    }

    @SuppressLint("StaticFieldLeak")
    private void getTodoTaskByIdAndInsert(int id) {
        new AsyncTask<Integer, Void, ToDoTask>() {
            @Override
            protected ToDoTask doInBackground(Integer... params) {
                return myApplication.getDatabase().toDoTaskDao().getToDoListById(params[0]);
            }

            @Override
            protected void onPostExecute(ToDoTask todoList) {
                recyclerViewAdapter.addRow(todoList);
            }
        }.execute(id);

    }

    @SuppressLint("StaticFieldLeak")
    private void loadAllTodoTasks() {
        new AsyncTask<String, Void, List<ToDoTask>>() {
            @Override
            protected List<ToDoTask> doInBackground(String... params) {
                return myApplication.getDatabase().toDoTaskDao().getAll();
            }

            @Override
            protected void onPostExecute(List<ToDoTask> todoList) {
                recyclerViewAdapter.updateTodoList(todoList);
            }
        }.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == NEW_TODO_REQUEST_CODE) {
                long id = data.getLongExtra("id", -1);
                Toast.makeText(getApplicationContext(), "Row inserted", Toast.LENGTH_SHORT).show();
                getTodoTaskByIdAndInsert((int) id);

            } else if (requestCode == UPDATE_TODO_REQUEST_CODE) {

                boolean isDeleted = data.getBooleanExtra("isDeleted", false);
                int number = data.getIntExtra("number", -1);
                if (isDeleted) {
                    Toast.makeText(getApplicationContext(), number + " rows deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), number + " rows updated", Toast.LENGTH_SHORT).show();
                }
                loadAllTodoTasks();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No action done by user", Toast.LENGTH_SHORT).show();
        }
    }


    private void checkIfAppLaunchedFirstTime() {
        final String PREFS_NAME = "SharedPrefs";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("firstTime", true)) {
            settings.edit().putBoolean("firstTime", false).apply();
        }
    }
}

