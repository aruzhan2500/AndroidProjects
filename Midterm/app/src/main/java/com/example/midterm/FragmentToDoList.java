package com.example.midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentToDoList extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<ToDoTask> toDoTaskList;
    CallBackFragment callBackFragment;
    private Button btnDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(toDoTaskList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        btnDetails = view.findViewById(R.id.btnDetails);

//        btnDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(callBackFragment != null){
//                    callBackFragment.changeFragment();
//                }
//            }
//        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toDoTaskList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            ToDoTask toDoTask = new ToDoTask(
                    i+1,
                    "MyToDoTask",
                    "What should do",
                    "Important",
                    "University",
                    "10:00am"
            );
            toDoTaskList.add(toDoTask);
        }
    }

    public void setCallBackFragment(CallBackFragment callBackFragment){
        this.callBackFragment = callBackFragment;
    }
}
