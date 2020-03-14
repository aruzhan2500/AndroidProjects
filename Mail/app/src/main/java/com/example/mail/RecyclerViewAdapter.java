package com.example.mail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Email[] emailList;
    private Context context;
    private EmailClick emailClick;

    public RecyclerViewAdapter(Email[] emailList, Context context) {
        this.emailList = emailList;
        this.context = context;
        this.emailClick = (EmailClick)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_item, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        viewHolder.tvEmailSender.setText(emailList[position].getEmailSender());
        viewHolder.tvEmailTitle.setText(emailList[position].getEmailTitle());
        viewHolder.tvEmailDetails.setText(emailList[position].getEmailDetails());
        viewHolder.tvEmailDuration.setText(emailList[position].getEmailDuration());

        viewHolder.tvIcon.setText(emailList[position].getEmailSender().substring(0, 1));

        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) viewHolder.tvIcon.getBackground()).setColor(color);

        viewHolder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return emailList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIcon;
        TextView tvEmailSender;
        TextView tvEmailTitle;
        TextView tvEmailDetails;
        TextView tvEmailDuration;
        ImageView ivFavorite;
        int position;

        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            tvIcon = itemView.findViewById(R.id.tvIcon);
            tvEmailSender = itemView.findViewById(R.id.tvEmailSender);
            tvEmailTitle = itemView.findViewById(R.id.tvEmailTitle);
            tvEmailDetails = itemView.findViewById(R.id.tvEmailDetails);
            tvEmailDuration = itemView.findViewById(R.id.tvEmailDuration);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailClick.emailClicked(emailList[position]);
                }
            });
        }

        public void setPosition(int position){
            this.position = position;
        }
    }

    interface EmailClick{
        void emailClicked(Email email);
    }
}
