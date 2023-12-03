package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    TextView username, name, email, phone_number, DOB,header;
    Bundle bundle;
    Intent intent;
    Button register,skip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        bundle = new Bundle();
        intent = new Intent(this, Search.class);
        username = findViewById(R.id.login_name);



    }


    }
