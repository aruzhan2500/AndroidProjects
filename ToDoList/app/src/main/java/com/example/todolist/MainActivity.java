package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ToDoTaskClick {

    AppDatabase database = MyApplication.getInstance().getDatabase();;
    static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new ToDoListFragment(database.toDoTaskDao().getAll(), this)).commit();
    }


    public void onAddToDo(View view) {
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new AddToDoFragment()).commit();
    }

    public void onCallBack(View view) {
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new ToDoListFragment(database.toDoTaskDao().getAll(), this)).commit();
    }

    @Override
    public void toDoTaskClicked(long id){
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new ToDoDetailFragment(database.toDoTaskDao().getToDoTask(id), this)).commit();
    }

    @Override
    public void update(long id, String title, String description, String status, long category, String duration) {
        database.toDoTaskDao().update(id, title, description, status, category, duration);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new ToDoListFragment(database.toDoTaskDao().getAll(), this)).commit();
    }

    @Override
    public void delete(long id) {
        database.toDoTaskDao().delete(id);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new ToDoListFragment(database.toDoTaskDao().getAll(), this)).commit();
    }

    @Override
    public void insert(ToDoTask toDoTask) {
        database.toDoTaskDao().insert(toDoTask);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                new ToDoListFragment(database.toDoTaskDao().getAll(), this)).commit();
    }

    @Override
    public String getCategoryName(long id) {
        return database.categoryDao().getCategory(id).name;
    }
}
