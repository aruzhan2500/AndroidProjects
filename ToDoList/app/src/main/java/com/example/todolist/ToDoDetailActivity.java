package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class ToDoDetailActivity extends AppCompatActivity {

    EditText title, description, status, duration;
    Spinner category;
    Button btnDone, btnDelete;
    boolean isNewTodoTask = false;

    private String[] categories = {
            "Android",
            "iOS",
            "Kotlin",
            "Swift"
    };

    public ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));
    MyApplication myApplication = MyApplication.getInstance();

    ToDoTask updateToDoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_detail);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status);
        category = findViewById(R.id.category);
        duration = findViewById(R.id.duration);

        btnDone = findViewById(R.id.btnDone);
        btnDelete = findViewById(R.id.btnDelete);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        final int id = getIntent().getIntExtra("id", -100);

        if(id == -100){
            isNewTodoTask = true;
            btnDelete.setVisibility(View.INVISIBLE);
        }
        else{
            getToDoTaskById(id);
            btnDelete.setVisibility(View.VISIBLE);
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNewTodoTask){
                    ToDoTask toDoTask = new ToDoTask();
                    toDoTask.title = title.getText().toString();
                    toDoTask.duration = duration.getText().toString();
                    toDoTask.category = category.getSelectedItem().toString();
                    toDoTask.status = status.getText().toString();
                    toDoTask.description = description.getText().toString();

                    insertRow(toDoTask);
                }
                else {
                    updateToDoTask.title = title.getText().toString();
                    updateToDoTask.duration = duration.getText().toString();
                    updateToDoTask.category = category.getSelectedItem().toString();
                    updateToDoTask.status = status.getText().toString();
                    updateToDoTask.description = description.getTag().toString();

                    updateRow(updateToDoTask);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(updateToDoTask);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void getToDoTaskById(final int id){
        new AsyncTask<Integer, Void, ToDoTask>() {
            @Override
            protected ToDoTask doInBackground(Integer... integers) {
                return myApplication.getDatabase().toDoTaskDao().getToDoListById(integers[0]);
            }
            @Override
            protected void onPostExecute(ToDoTask toDoTask){
                super.onPostExecute(toDoTask);
                title.setText(toDoTask.title);
                description.setText(toDoTask.description);
                status.setText(toDoTask.status);
                category.setSelection(spinnerList.indexOf(toDoTask.category));
                duration.setText(toDoTask.duration);

                updateToDoTask = toDoTask;
            }
        }.execute(id);
    }

    @SuppressLint("StaticFieldLeak")
    private void insertRow(ToDoTask toDoTask){
        new AsyncTask<ToDoTask, Void, Long>() {
            @Override
            protected Long doInBackground(ToDoTask... params) {
                return myApplication.getDatabase().toDoTaskDao().insertToDoTask(params[0]);
            }

            @Override
            protected void onPostExecute(Long id){
                super.onPostExecute(id);

                Intent intent = getIntent();
                intent.putExtra("isNew", true).putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(toDoTask);
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteRow(ToDoTask todo) {
        new AsyncTask<ToDoTask, Void, Integer>() {
            @Override
            protected Integer doInBackground(ToDoTask... params) {
                return myApplication.getDatabase().toDoTaskDao().deleteToDoTask(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isDeleted", true).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }

    @SuppressLint("StaticFieldLeak")
    private void updateRow(ToDoTask todo) {
        new AsyncTask<ToDoTask, Void, Integer>() {
            @Override
            protected Integer doInBackground(ToDoTask... params) {
                return myApplication.getDatabase().toDoTaskDao().updateToDoTask(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isNew", false).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);
    }
}
