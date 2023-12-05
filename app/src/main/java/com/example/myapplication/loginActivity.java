package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;


public class loginActivity extends AppCompatActivity {

    TextView username, name, email, phone_number, DOB, header;
    Bundle bundle;
    Intent intent;
    Button register, skip;
    EditText editTextdob;
    ImageView datepicker;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = findViewById(R.id.login_username);
        editTextdob = findViewById(R.id.login_DOB);
        datepicker = findViewById(R.id.datpicker);
        datepicker.setOnClickListener(v -> showDatePickerDialog());
    }

    //  date picker dialog
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    editTextdob.setText(selectedDate);
                },
                2000, // default year
                0,    // default month (January)
                1     // default day of the month
        );
        datePickerDialog.show();
    }

    }
