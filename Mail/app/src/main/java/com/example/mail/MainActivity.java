package com.example.mail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.EmailClick {

    static FragmentManager fragmentManager;
    private MailListFragment mailListFragment;
    private MailDetailFragment mailDetailFragment;
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = getIntent().getStringExtra("Email logged");
        mailListFragment = new MailListFragment();
        mailDetailFragment = new MailDetailFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, mailListFragment).commit();
    }


    @Override
    public void emailClicked(Email email) {
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new MailDetailFragment(email)).commit();
    }

    public void onCallBack(View view) {
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, mailListFragment).commit();
    }
}
