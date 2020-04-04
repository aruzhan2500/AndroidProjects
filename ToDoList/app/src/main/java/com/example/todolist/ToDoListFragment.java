package com.example.todolist;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ToDoListFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ToDoTask> toDoList;
    Context context;

    public ToDoListFragment() {
    }

    public ToDoListFragment(List<ToDoTask> toDoList, Context context){
        this.toDoList = toDoList;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(toDoList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
