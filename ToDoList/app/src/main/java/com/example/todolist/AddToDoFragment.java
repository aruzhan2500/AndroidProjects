package com.example.todolist;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddToDoFragment extends Fragment {

    RecyclerViewAdapter.ToDoTaskClick toDoTaskClick;
    EditText title, description, status, category, duration;
    Button insert;
    View view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toDoTaskClick = (RecyclerViewAdapter.ToDoTaskClick)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_to_do, container, false);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        status = view.findViewById(R.id.status);
        description = view.findViewById(R.id.description);
        category = view.findViewById(R.id.category);
        duration = view.findViewById(R.id.duration);
        insert = view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoTask toDoTask = new ToDoTask();
                toDoTask.title = title.getText().toString();
                toDoTask.description = description.getText().toString();
                toDoTask.status = description.getText().toString();
                toDoTask.category = Long.parseLong(category.getText().toString());
                toDoTaskClick.insert(toDoTask);
            }
        });
        return view;
    }

}
