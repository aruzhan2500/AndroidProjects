package com.example.midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentToDoDetail extends Fragment {

    private View view;
    TextView todoItemId, todoItemTitle, todoItemDescription, todoItemStatus, todoItemCategory, todoItemDurations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_todo_detail, container, false);
        todoItemId = view.findViewById(R.id.todoItemId);
        todoItemTitle = view.findViewById(R.id.todoItemTitle);
        todoItemDescription = view.findViewById(R.id.todoItemDescription);
        todoItemStatus = view.findViewById(R.id.todoItemStatus);
        todoItemCategory = view.findViewById(R.id.todoItemCategory);
        todoItemDurations = view.findViewById(R.id.todoItemDurations);

        return view;
    }




}
