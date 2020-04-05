package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ToDoTask> toDoList;
    private ClickListener clickListener;

    public RecyclerViewAdapter(List<ToDoTask> toDoList,@Nullable ClickListener clickListener){
        this.toDoList = toDoList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_task_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ToDoTask toDoTask = toDoList.get(position);
        holder.title.setText(toDoTask.title);
        holder.status.setText(toDoTask.status);
        holder.category.setText(toDoTask.category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.launchIntent(position, toDoTask);
                }
            }
        });
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
        }
    }

    public interface ClickListener {
        void launchIntent(int id, ToDoTask toDoTask);
    }
}
