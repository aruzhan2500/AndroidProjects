package com.example.mail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MailDetailFragment extends Fragment {

    private TextView tvEmailTitle;
    private ImageView ivFavorite;
    private TextView tvIcon;
    private TextView tvEmailSender;
    private TextView tvEmailDuration;
    private TextView tvEmailDetails;
    Email email;
    boolean detail = true;

    public MailDetailFragment(){
        this.detail = false;
    }

    public MailDetailFragment(Email email){
        this.email = email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(detail != false){
            tvEmailTitle = view.findViewById(R.id.tvEmailTitle);
            ivFavorite = view.findViewById(R.id.ivFavorite);
            tvIcon = view.findViewById(R.id.tvIcon);
            tvEmailSender = view.findViewById(R.id.tvEmailSender);
            tvEmailDuration = view.findViewById(R.id.tvEmailDuration);
            tvEmailDetails = view.findViewById(R.id.tvEmailDetails);

            tvEmailTitle.setText(email.getEmailTitle());
            tvEmailSender.setText(email.getEmailSender());
            tvEmailDuration.setText(email.getEmailDuration());
            tvEmailDetails.setText(email.getEmailDetails());
        }
    }
}
