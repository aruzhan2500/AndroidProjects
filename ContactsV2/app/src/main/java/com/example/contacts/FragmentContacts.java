package com.example.contacts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentContacts extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<Contact> contacts;

    public FragmentContacts(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater. inflate(R.layout.fragment_contacts, container, false);
        recyclerView = view.findViewById(R.id.contactRecyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contacts = new ArrayList<>();
        contacts.add(new Contact("First Person", "1111", R.drawable.first));
        contacts.add(new Contact("Second Person", "2222", R.drawable.second));
        contacts.add(new Contact("Third Person", "3333", R.drawable.third));
        contacts.add(new Contact("Forth Person", "4444", R.drawable.forth));
        contacts.add(new Contact("Fifth Person", "5555", R.drawable.fifth));
        contacts.add(new Contact("Sixth Person", "6666", R.drawable.sixth));
        contacts.add(new Contact("Seventh Person", "7777", R.drawable.seventh));
        contacts.add(new Contact("Eighth Person", "8888", R.drawable.eighth));
        contacts.add(new Contact("Ninth Person", "9999", R.drawable.ninth));
        contacts.add(new Contact("Tenth Person", "10101010", R.drawable.tenth));
    }
}
