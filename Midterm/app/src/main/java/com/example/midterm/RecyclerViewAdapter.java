package com.example.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ToDoTask> toDoTasks;
    private Context context;

    public RecyclerViewAdapter(List<ToDoTask> toDoTasks, Context context) {
        this.toDoTasks = toDoTasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTodoItemTitle.setText(toDoTasks.get(position).getTitle());
        holder.tvTodoItemStatus.setText(toDoTasks.get(position).getStatus());
        holder.tvTodoItemCategory.setText(toDoTasks.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return toDoTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTodoItemTitle;
        public TextView tvTodoItemStatus;
        public TextView tvTodoItemCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTodoItemTitle = itemView.findViewById(R.id.todoItemTitle);
            tvTodoItemStatus = itemView.findViewById(R.id.todoItemStatus);
            tvTodoItemCategory = itemView.findViewById(R.id.todoItemCategory);
        }


    }
}
