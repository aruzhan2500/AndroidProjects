package com.example.todolist;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ToDoDetailFragment extends Fragment {
    private EditText id, title, description, status, category, duration;
    ToDoTask toDoTask;
    Button update, delete;
    RecyclerViewAdapter.ToDoTaskClick toDoTaskClick;

    public ToDoDetailFragment() {

    }

    public ToDoDetailFragment(ToDoTask toDoTask, Context context){
        this.toDoTask = toDoTask;
        toDoTaskClick = (RecyclerViewAdapter.ToDoTaskClick)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.id);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        status = view.findViewById(R.id.status);
        category = view.findViewById(R.id.category);
        duration = view.findViewById(R.id.duration);

        title.setText(toDoTask.title);
        description.setText(toDoTask.description);
        status.setText(toDoTask.status);
        category.setText(toDoTask.category + "");
        duration.setText(toDoTask.duration);

        update = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoTaskClick = (RecyclerViewAdapter.ToDoTaskClick)view.getContext();
                toDoTaskClick.update(toDoTask.id, title.getText().toString(), description.getText().toString(), status.getText().toString(),
                Long.parseLong(category.getText().toString()), duration.getText().toString());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoTaskClick = (RecyclerViewAdapter.ToDoTaskClick)view.getContext();
                toDoTaskClick.delete(toDoTask.id);
            }
        });
    }
}
