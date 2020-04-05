package com.example.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;


public class AddToDoFragment extends Fragment {

    AppDatabase appDatabase;
    ToDoTaskDao toDoTaskDao;

    EditText title, description, status, duration;
    Spinner category;
    Button btnAdd;

    private String[] categories = {
            "Android",
            "iOS",
            "Kotlin",
            "Swift"
    };

    public ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));

    public AddToDoFragment() {

    }
    public static AddToDoFragment newInstance() {
        return new AddToDoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_to_do, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        status = view.findViewById(R.id.status);
        duration = view.findViewById(R.id.duration);
        category = view.findViewById(R.id.category);
        btnAdd = view.findViewById(R.id.btnAdd);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoTask toDoTask = new ToDoTask();
                toDoTask.title = title.getText().toString();
                toDoTask.description = description.getText().toString();
                toDoTask.duration = duration.getText().toString();
                toDoTask.category = category.getSelectedItem().toString();
                try {
                    toDoTaskDao.insertToDoTask(toDoTask);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, MainFragment.newInstance())
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }
                catch (Exception e){
                    Log.e("Failed to insert", e.toString());
                }

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appDatabase = MyApplication.getInstance().getDatabase();
        toDoTaskDao = appDatabase.toDoTaskDao();
    }
}
