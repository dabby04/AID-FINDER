package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentNotificationsBinding;

//package com.example.myapplication.ui.notifications;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNotificationsBinding;

import java.io.FileOutputStream;

public class NotificationsFragment extends Fragment {
    TextView username, name, email, phone_number, DOB,header;
    Bundle bundle;
    Intent intent;
    Button register;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.profileHeader;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        username = (TextView) root.findViewById(R.id.profile_username);
        name = (TextView) root.findViewById(R.id.login_name);
        email = (TextView) root.findViewById(R.id.login_email);
        phone_number = (TextView) root.findViewById(R.id.login_phoneNumber);
        DOB = (TextView) root.findViewById(R.id.profile_dateOfBirthTextview);

        register = root.findViewById(R.id.login_skip_button);
        register.setVisibility(View.GONE);

        header = root.findViewById(R.id.profile_header);
        header.setVisibility(View.GONE);

        bundle = intent.getExtras();

        if(bundle.isEmpty()){
            header.setVisibility(View.VISIBLE);

        }else{
            username.append("" + bundle.get("username") );
            name.append("" + bundle.get("name") );
            email.append("" + bundle.get("email") );
            phone_number.append("" + bundle.get("phone number") );
            DOB.append("" + bundle.get("date of birth") );
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name.getText().toString();
                String u = username.getText().toString();
                String e = email.getText().toString();
                String p = phone_number.getText().toString();
                String d = DOB.getText().toString();

                FileOutputStream outputStream;
                String filename = "data.txt";
                String fileContents = "\n" + n + "\n" + u + "\n" + e + "\n" + p + "\n" + d;

            }
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}