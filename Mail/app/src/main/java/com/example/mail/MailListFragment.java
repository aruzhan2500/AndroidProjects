package com.example.mail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MailListFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Email> emailList;

    public MailListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mail_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(emailList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        emailList = new ArrayList<>();
        Email email = new Email("Sam", "Weekend adventure",
                "Let's go fishing with John and others. We will do some barbecue and have so much fun",
                "10:42am");
        emailList.add(email);
        email = new Email("Facebook", "James, you have 1 new notification",
                "A lot has happened on Facebook since",
                "16:04pm");
        emailList.add(email);
        email = new Email("Google+", "Top suggested Google+ pages for you",
                "Top suggested Google+ pages for you",
                "18:44pm");
        emailList.add(email);
        email = new Email("Twitter", "Follow T-Mobile, Samsung Mobile U",
                "James, some people you may know",
                "20:04pm");
        emailList.add(email);
        email = new Email("Pinterest Weekly", "Pins you’ll love!",
                "Have you seen these Pins yet? Pinterest",
                "09:04am");
        emailList.add(email);
        email = new Email("Josh", "Going lunch", "Don't forget our lunch at 3PM in Pizza hut",
                "01:04am");
        emailList.add(email);
        email = new Email("Sam", "Weekend adventure",
                "Let's go fishing with John and others. We will do some barbecue and have so much fun",
                "10:42am");
        emailList.add(email);
        email = new Email("Facebook", "James, you have 1 new notification",
                "A lot has happened on Facebook since",
                "16:04pm");
        emailList.add(email);
        email = new Email("Google+", "Top suggested Google+ pages for you",
                "Top suggested Google+ pages for you",
                "18:44pm");
        emailList.add(email);
        email = new Email("Twitter", "Follow T-Mobile, Samsung Mobile U",
                "James, some people you may know",
                "20:04pm");
        emailList.add(email);
        email = new Email("Pinterest Weekly", "Pins you’ll love!",
                "Have you seen these Pins yet? Pinterest",
                "09:04am");
        emailList.add(email);
        email = new Email("Josh", "Going lunch", "Don't forget our lunch at 3PM in Pizza hut",
                "01:04am");
        emailList.add(email);

    }
}
