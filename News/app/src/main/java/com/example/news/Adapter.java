package com.example.news;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.news.models.Article;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Article> articles;
    private Context context;

    public Adapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    private OnItemClickListener onItemClickListener;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyViewHolder myViewHolder = holder;
        Article model = articles.get(position);

        Picasso.get().load(model.getUrlToImage())
                .into(myViewHolder.img, new Callback() {
                    @Override
                    public void onSuccess() {
                        myViewHolder.progress_load_photo.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        myViewHolder.progress_load_photo.setVisibility(View.GONE);
                    }
                });

        myViewHolder.title.setText(model.getTitle());
        myViewHolder.source.setText(model.getSource().getName());
        myViewHolder.desc.setText(model.getDescription());
        myViewHolder.publishedAt.setText(Utils.DateFormat(model.getPublishedAt()));
        myViewHolder.time.setText(" \u2022 " + Utils.DateToTimeFormat(model.getPublishedAt()));
        myViewHolder.author.setText(model.getAuthor());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, source, author, desc, publishedAt, time;
        ImageView img;
        ProgressBar progress_load_photo;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source);
            author = itemView.findViewById(R.id.author);
            desc = itemView.findViewById(R.id.desc);
            publishedAt = itemView.findViewById(R.id.publishedAt);
            time = itemView.findViewById(R.id.time);
            img = itemView.findViewById(R.id.img);
            progress_load_photo = itemView.findViewById(R.id.progress_load_photo);

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
