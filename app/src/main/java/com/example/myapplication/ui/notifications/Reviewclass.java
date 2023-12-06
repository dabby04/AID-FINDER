package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Reviewclass extends AppCompatActivity{



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewiew_page1);
        Log.d("Reviewclass", "Reviewclass activity started");

        RatingBar ratingBar = findViewById(R.id.ratingBar);

//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar) {
//
//                Toast.makeText(getApplicationContext(), "Rated: " + rating, Toast.LENGTH_SHORT).show();
//
//
//            }
//        });

    }

    public void skip(View v){
        finish();
    }

}
