package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ToDoTask> toDoList;
    private Context context;
    private ToDoTaskClick toDoTaskClick;

    public RecyclerViewAdapter(List<ToDoTask> toDoList, Context context){
        this.toDoList = toDoList;
        this.context = context;
        this.toDoTaskClick = (ToDoTaskClick)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_task_item, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        viewHolder.title.setText(toDoList.get(position).title);
        viewHolder.status.setText(toDoList.get(position).status);
        viewHolder.category.setText(toDoTaskClick.getCategoryName(toDoList.get(position).category));
        viewHolder.id = toDoList.get(position).id;
        viewHolder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, status, category;
        int position;
        long id;

        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            category = itemView.findViewById(R.id.category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDoTaskClick.toDoTaskClicked(id);
                }
            });
        }

        public void setPosition(int position){
            this.position = position;
        }
    }

    interface ToDoTaskClick{
        void toDoTaskClicked(long id);
        void delete(long id);
        void update(long id, String title, String description, String status, long category, String duration);
        void insert(ToDoTask toDoTask);
        String getCategoryName(long id);
    }
}
