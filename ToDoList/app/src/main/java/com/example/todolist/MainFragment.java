package com.example.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainFragment extends Fragment {
    AppDatabase appDatabase;
    ToDoTaskDao toDoTaskDao;
    List<ToDoTask> toDoList;
    private RecyclerViewAdapter.ClickListener clickListener = null;

    FloatingActionButton floatingActionButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        appDatabase = MyApplication.getInstance().getDatabase();
        toDoTaskDao = appDatabase.toDoTaskDao();
        toDoList = toDoTaskDao.getAll();
        clickListener = new RecyclerViewAdapter.ClickListener() {
            @Override
            public void launchIntent(int id, ToDoTask toDoTask) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ToDoDetailFragment.newInstance(toDoTask))
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        };
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, AddToDoFragment.newInstance())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(toDoList, clickListener);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
