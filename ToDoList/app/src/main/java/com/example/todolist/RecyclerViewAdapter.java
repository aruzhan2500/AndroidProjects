package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ToDoTask> toDoList;
    private RecyclerViewAdapter.ClickListener clickListener;

    public RecyclerViewAdapter(ClickListener clickListener){
        this.toDoList = new ArrayList<>();
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_task_item, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoTask toDoTask = toDoList.get(position);
        holder.title.setText(toDoTask.title);
        holder.status.setText(toDoTask.status);
        holder.category.setText(toDoTask.category);
    }

    public void updateTodoList(List<ToDoTask> toDoList) {
        toDoList.clear();
        toDoList.addAll(toDoList);
        notifyDataSetChanged();
    }

    public void addRow(ToDoTask toDoTask) {
        toDoList.add(toDoTask);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, status, category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            category = itemView.findViewById(R.id.category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(toDoList.get(getAdapterPosition()).id);
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(int id);
    }

}
