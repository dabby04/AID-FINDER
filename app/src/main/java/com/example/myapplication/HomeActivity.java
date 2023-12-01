package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.home.HomeFragment;

public class HomeActivity extends AppCompatActivity {
    RadioGroup category;
    CheckBox food,house, community;
    TextView head;
    boolean needsFood,needsHousing,needsCommunity;
    Bundle bundle;
    Intent intent;

    Button next,prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page2);

        bundle = new Bundle();
        intent = new Intent(this,Search.class);

        category = (RadioGroup) findViewById(R.id.radioGroup);
        food = (CheckBox) findViewById(R.id.checkBox);
        house = (CheckBox) findViewById(R.id.checkBox2);
        community = (CheckBox) findViewById(R.id.checkBox3);
        head = (TextView) findViewById(R.id.header2);
        next = (Button) findViewById(R.id.nextButton);
        prev = (Button) findViewById(R.id.prevButton2);

        needsFood = false;
        needsCommunity = false;
        needsHousing = false;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        // Set click listener for the "Prev" button
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(HomeActivity.this, HomeFragment.class));

            }
        });

    }
    public void foodCheck(View view){
         if(food.isChecked())
            needsFood = true;

        bundle.putBoolean("food",needsFood);
    }
    public void houseCheck(View view){

        if(house.isChecked())
            needsHousing = true;

        bundle.putBoolean("housing",needsHousing);
    }

    public void communityCheck(View view){

        if(community.isChecked())
            needsCommunity = true;

        bundle.putBoolean("community",needsCommunity);
    }


}
