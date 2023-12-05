package com.example.myapplication.ui.notifications;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Reviewclass;
import com.example.myapplication.Search;
import com.example.myapplication.databinding.FragmentNotificationsBinding;
//package com.example.myapplication.ui.notifications;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.dashboard.DashboardFragment;

import java.util.Calendar;

public class NotificationsFragment extends Fragment {
    TextView username, name, email, phone_number, DOB, header;
    Bundle bundle;
    Intent intent;
    Button register;
    Button leaveAReview;
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
        register = root.findViewById(R.id.signup_submit_button2);
        header = root.findViewById(R.id.profile_header);
        header.setVisibility(View.GONE);
        leaveAReview = root.findViewById(R.id.LAR);

        //leave a review button connection
        register = root.findViewById(R.id.signup_submit_button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("NotificationsFragment", "Leave a review button clicked");
                //Intent reviewIntent = new Intent(getActivity(), Reviewclass.class);
                //startActivity(reviewIntent);
            }
        });

        leaveAReview = root.findViewById(R.id.LAR);
        leaveAReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(getActivity(), Reviewclass.class);
                startActivity(reviewIntent);
            }
        });




        ImageView imageView4 = root.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return root;
    }

    // date picker
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    String selectedDate = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    DOB.setText(selectedDate);
                },
                year, // default year
                month, // default month
                dayOfMonth // default day
        );

        datePickerDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}