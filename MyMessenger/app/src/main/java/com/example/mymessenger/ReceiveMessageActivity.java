package com.example.mymessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

    private TextView textView;
    public final static String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        textView = findViewById(R.id.message);
        Intent intent = getIntent();
        String string = intent.getStringExtra(EXTRA_MESSAGE);
        textView.setText(string);
    }


}
