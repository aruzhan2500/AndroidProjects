package com.example.todolist;

import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;


public class ToDoDetailFragment extends Fragment {

    AppDatabase appDatabase = MyApplication.getInstance().getDatabase();
    ToDoTaskDao toDoTaskDao = appDatabase.toDoTaskDao();

    EditText id, title, description, status, duration;
    Spinner category;
    Button btnDone, btnDelete;

    private String[] categories = {
            "Android",
            "iOS",
            "Kotlin",
            "Swift"
    };

    public ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));

    public ToDoDetailFragment() {
    }

    public static ToDoDetailFragment newInstance(ToDoTask toDoTask) {
        ToDoDetailFragment fragment = new ToDoDetailFragment();
        Bundle args = new Bundle();
        args.putString("id", toDoTask.id + "");
        args.putString("title", toDoTask.title);
        args.putString("description", toDoTask.description);
        args.putString("status", toDoTask.status);
        args.putString("duration", toDoTask.duration);
        args.putString("category", toDoTask.category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_detail, container, false);

        btnDone = view.findViewById(R.id.btnDone);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toDoTask_id = Integer.parseInt(id.getText().toString());
                ToDoTask toDoTask = toDoTaskDao.getToDoTaskById(toDoTask_id);
                toDoTask.title = title.getText().toString();
                toDoTask.description = description.getText().toString();
                toDoTask.status = status.getText().toString();
                toDoTask.duration = duration.getText().toString();
                toDoTask.category = category.getSelectedItem().toString();
                toDoTaskDao.updateToDoTask(toDoTask);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toDoTask_id = Integer.parseInt(id.getText().toString());
                ToDoTask toDoTask = toDoTaskDao.getToDoTaskById(toDoTask_id);
                toDoTaskDao.deleteToDoTask(toDoTask);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.id);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        status = view.findViewById(R.id.status);
        duration = view.findViewById(R.id.duration);
        category = view.findViewById(R.id.category);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        id.setText(getArguments().getString("id"));
        title.setText(getArguments().getString("title"));
        description.setText(getArguments().getString("description"));
        status.setText(getArguments().getString("status"));
        duration.setText(getArguments().getString("duration"));
        category.setSelection(spinnerList.indexOf(getArguments().getString("category")));
    }
}
