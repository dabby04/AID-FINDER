package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.ui.dashboard.DashboardFragment;

public class loginFragment extends Fragment {

    public loginFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.signup, container, false);


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views and set click listener here
        Button submit = view.findViewById(R.id. login_submit_button);
        submit.setOnClickListener(this::submit);
        Button skip = view.findViewById(R.id. login_skip_button);
        skip.setOnClickListener(this::skip);

    }

//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null){
//               getFragmentManager().beginTransaction().add(android.R.id.content, new DashboardFragment()).commit();
//        }
//
//    }

    public void submit(View view){
        FragmentManager fragmentManager =  requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BlankFragment newFragment = new BlankFragment();
        fragmentTransaction.replace(R.id.f, newFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void skip(View view){
        FragmentManager fragmentManager =  requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BlankFragment newFragment = new BlankFragment();
        fragmentTransaction.replace(R.id.f, newFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }




}
