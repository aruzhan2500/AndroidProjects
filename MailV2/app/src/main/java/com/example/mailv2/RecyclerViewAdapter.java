package com.example.mailv2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Email> emailList;
    private Context context;

    public RecyclerViewAdapter(List<Email> emailList, Context context) {
        this.emailList = emailList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvEmailSender.setText(emailList.get(position).getEmailSender());
        holder.tvEmailTitle.setText(emailList.get(position).getEmailTitle());
        holder.tvEmailDetails.setText(emailList.get(position).getEmailDetails());
        holder.tvEmailDuration.setText(emailList.get(position).getEmailDuration());

        holder.tvIcon.setText(emailList.get(position).getEmailSender().substring(0, 1));

        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.tvIcon.getBackground()).setColor(color);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MailDetailFragment.class);
                intent.putExtra("sender", holder.tvEmailSender.getText().toString());
                intent.putExtra("title", holder.tvEmailTitle.getText().toString());
                intent.putExtra("details", holder.tvEmailDetails.getText().toString());
                intent.putExtra("duration", holder.tvEmailDuration.getText().toString());
                intent.putExtra("icon", holder.tvIcon.getText().toString());
                intent.putExtra("colorIcon", color);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return emailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIcon;
        TextView tvEmailSender;
        TextView tvEmailTitle;
        TextView tvEmailDetails;
        TextView tvEmailDuration;
        ImageView ivFavorite;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIcon = itemView.findViewById(R.id.tvIcon);
            tvEmailSender = itemView.findViewById(R.id.tvEmailSender);
            tvEmailTitle = itemView.findViewById(R.id.tvEmailTitle);
            tvEmailDetails = itemView.findViewById(R.id.tvEmailDetails);
            tvEmailDuration = itemView.findViewById(R.id.tvEmailDuration);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);
            layout = itemView.findViewById(R.id.mailItem);
        }
    }
}
