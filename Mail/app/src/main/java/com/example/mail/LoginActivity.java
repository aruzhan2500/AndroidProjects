package com.example.mail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.view.Window.FEATURE_NO_TITLE;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sharedPreferences = getSharedPreferences("usersFile", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("email", "Email did not log").equals("Email did not log")){
            Intent login = new Intent(this, MainActivity.class);
            login.putExtra("Email logged", sharedPreferences.getString("email", "Email did not log"));
            startActivity(login);
            finish();
        }
    }

    public void onLogin(View view) {
        Intent login = new Intent(LoginActivity.this, MainActivity.class);
        if(sharedPreferences.getString("email", "Email did not log").equals("Email did not log")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email.getText().toString());
            editor.putString("password", password.getText().toString());
            editor.apply();
            login.putExtra("Email logged", sharedPreferences.getString("email", "Email did not log"));
            startActivity(login);
            finish();
        }
    }

    public static void onLogOut(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
