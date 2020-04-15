package com.example.developerjobs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class JobDetailFragment extends Fragment {

    private TextView title;
    private TextView type;
    private TextView created_at;
    private TextView company;
    private TextView location;
    private TextView description;
    private TextView how_to_apply;
    private ImageView logo;
    private LinearLayout third_card, forth_card;

    public static JobDetailFragment newInstance(Job job) {
        JobDetailFragment fragment = new JobDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", job.getTitle());
        args.putString("type", job.getType());
        args.putString("created_at", job.getCreatedAt());
        args.putString("company", job.getCompany());
        args.putString("location", job.getLocation());
        args.putString("how_to_apply", job.getHowToApply());
        args.putString("description", job.getDescription());
        args.putString("logo", job.getCompanyLogo());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_job_detail, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Job details ");

        third_card = view.findViewById(R.id.third_card);
        forth_card = view.findViewById(R.id.forth_card);

        third_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setText(Html.fromHtml(getArguments().getString("description")));
            }
        });

        forth_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                how_to_apply.setText(Html.fromHtml(getArguments().getString("how_to_apply")));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title);
        type = view.findViewById(R.id.type);
        created_at = view.findViewById(R.id.created_at);
        company = view.findViewById(R.id.company);
        location = view.findViewById(R.id.location);
        description = view.findViewById(R.id.description);
        how_to_apply = view.findViewById(R.id.how_to_apply);
        logo = view.findViewById(R.id.logo);

        try{
            Picasso.get()
                    .load(getArguments().getString("logo"))
                    .placeholder(R.drawable.ic_import_contacts_black_24dp)
                    .error(R.drawable.ic_import_contacts_black_24dp)
                    .resize(200, 200)
                    .into(logo);

            title.setText(getArguments().getString("title"));
            type.setText(getArguments().getString("type"));
            created_at.setText(getArguments().getString("created_at"));
            company.setText(getArguments().getString("company"));
            location.setText(getArguments().getString("location"));
        }
        catch (Exception e){
            Log.e("error:", e + "");
        }
    }
}
