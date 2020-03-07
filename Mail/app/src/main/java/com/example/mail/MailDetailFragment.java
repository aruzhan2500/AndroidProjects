package com.example.mail;

import android.content.Intent;
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

    public MailDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail_detail, container, false);
        tvEmailTitle = view.findViewById(R.id.tvEmailTitle);
        ivFavorite = view.findViewById(R.id.ivFavorite);
        tvIcon = view.findViewById(R.id.tvIcon);
        tvEmailSender = view.findViewById(R.id.tvEmailSender);
        tvEmailDuration = view.findViewById(R.id.tvEmailDuration);
        tvEmailDetails = view.findViewById(R.id.tvEmailDetails);

        tvEmailSender.setText(getActivity().getIntent().getExtras().getString("sender"));
        tvEmailTitle.setText(getActivity().getIntent().getExtras().getString("title"));
        tvIcon.setText(getActivity().getIntent().getExtras().getString("icon"));
        tvEmailDuration.setText(getActivity().getIntent().getExtras().getString("duration"));
        tvEmailDetails.setText(getActivity().getIntent().getExtras().getString("details"));
        tvIcon.setBackground(Drawable.createFromPath(getActivity().getIntent().getExtras().getString("colorIcon")));

        return view;
    }
}
