package com.example.myapplication.ui.dashboard;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.location.Address;
import android.location.Geocoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private TextView locationText;
    private LocationRequest locationRequest;
    double latitude;
    double longitude;

    static Map<String,List<String>> details;
    static List<LatLng> markers;

//    Things remaining for me to do:
//    Check if my onclick for the markers are working
//    show the details of the marks clicked, use the image and details of the store
//    allow the close button to close the details of the store and lead back to the map
//    when done navigating, allow the user to click take me there close navigation completely-- starred concept
//    remember to change the previous part to finish() in home activity
//    in home view moodel, remove the settEXT
//    DELETE SEARCh
//    moving the home part to navifation part on the dashboad, essentially linking search

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        locationText = root.findViewById(R.id.locationText);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        ImageButton getLocationButton = root.findViewById(R.id.imageButton);
        getLocationButton.setOnClickListener(this::getLocation);

        Button next=root.findViewById(R.id.next_map);
        next.setOnClickListener(this::next);

        return root;
    }

    public void getLocation(View view){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                if(isGPSEnabled()){
                    LocationServices.getFusedLocationProviderClient(requireActivity())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    LocationServices.getFusedLocationProviderClient(requireActivity())
                                            .removeLocationUpdates(this);

                                    if(locationResult!=null && locationResult.getLocations().size()>0){
                                        int index=locationResult.getLocations().size()-1;
                                         latitude= locationResult.getLocations().get(index).getLatitude();
                                         longitude=locationResult.getLocations().get(index).getLongitude();

//                                        locationText.setText(latitude+", "+longitude);
                                        getAddressFromLocation(latitude, longitude);


                                    }
                                }
                            }, Looper.getMainLooper());
                }
                else{
                    turnOnGPS();
                }
            }
            else{
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
    public void next(View view){
        details=readFile();
        List<String> address=details.get("Address");
        Button nextButton = (Button) getView().findViewById(R.id.next_map);


        for(int i=0;i<address.size();i++){
            markers.add(getLocationFromAddress(requireContext(),address.get(i)));
        }
        String a=locationText.getText().toString();
        String[] split = a.split(",");
        if(split.length==2){
            latitude=Double.parseDouble(split[0].trim());
            longitude=Double.parseDouble(split[1].trim());
            nextButton.setVisibility(View.GONE);
            showMapFragment(latitude, longitude);
        }
        else{
            LatLng location = getLocationFromAddress(requireContext(), a);
            if (location != null) {
                nextButton.setVisibility(View.GONE);
                showMapFragment(location.latitude, location.longitude);
            } else {
                // Handle invalid address
            }
        }
    }
    public static LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng latLng = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null || address.isEmpty()) {
                return null;
            }

            Address location = address.get(0);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            latLng = new LatLng(latitude, longitude);

            return latLng;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void getAddressFromLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(requireContext());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String addressString = address.getAddressLine(0); // Get the full address
                locationText.setText(addressString);
            } else {
                locationText.setText("Address not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
            locationText.setText("Error fetching address");
        }
    }

    private void turnOnGPS(){
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(requireContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(requireActivity(), "GPS is already turned on", Toast.LENGTH_SHORT).show();


                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(requireActivity(), 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }
    private boolean isGPSEnabled(){
        LocationManager locationManager=null;
        boolean isEnabled=false;

        if(locationManager==null){
            locationManager=(LocationManager)requireContext().getSystemService(Context.LOCATION_SERVICE);
        }
        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void showMapFragment(double latitude, double longitude) {
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        mapsFragment.setArguments(bundle);

        // Replace the content of the mapsContainer with MapsFragment
        getChildFragmentManager().beginTransaction()
                .replace(R.id.mapsContainer, mapsFragment)
                .addToBackStack(null)
                .commit();

        // Show the container
        getView().findViewById(R.id.mapsContainer).setVisibility(View.VISIBLE);
    }

//    method that reads from the file and stores the latitude and longitude in lists
    public Map<String,List<String>> readFile(){
        Resources resources = getResources();
        InputStream inputStream = resources.openRawResource(R.raw.data);

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
//           // Read the headers
            String[] headers = reader.readNext();

            // Create a map to store lists for each header
            Map<String, List<String>> headerLists = new HashMap<>();
            for (String header : headers) {
                headerLists.put(header, new ArrayList<>());
            }

            // Read and process the CSV data
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Process the CSV data in 'nextLine'
                for (int i = 0; i < headers.length && i < nextLine.length; i++) {
                    // Add each value to the corresponding list
                    headerLists.get(headers[i]).add(nextLine[i]);
                }
            }
            return headerLists;
//            reader.close();
        }
        catch(IOException e){
            Log.d("FILE","FILE NOT FOUND");
        }
        return null;
    }


}