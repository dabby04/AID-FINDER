package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Search  extends AppCompatActivity {

    TextView head,foot,location;
    Button next,prev;
    Bundle bundle;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);


    }

}

