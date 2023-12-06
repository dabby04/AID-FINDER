package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.HomeActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.loginActivity;

public class HomeFragment extends Fragment {

    Button skip;
    TextView number1, number2, number3, number4,header,contact;
    private FragmentHomeBinding binding;

    FragmentTransaction transaction;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        skip = root.findViewById(R.id.nextButton2);
        number1 = root.findViewById(R.id.text_Number1);
        number2 = root.findViewById(R.id.text_Number2);
        number3 = root.findViewById(R.id.text_Number3);
        number4 = root.findViewById(R.id.text_Number4);
        header = root.findViewById(R.id.header1);
        contact = root.findViewById(R.id.foot1);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), loginActivity.class);
                startActivity(intent);

                FragmentManager fragmentManager =  requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                BlankFragment newFragment = new BlankFragment();
                fragmentTransaction.replace(R.id.f, newFragment);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
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