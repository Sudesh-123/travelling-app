package com.example.travel_sri_lanka.Maps;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;

import com.example.travel_sri_lanka.R;
import com.example.travel_sri_lanka.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Declares a GoogleMap object
    private ActivityMapsBinding binding; // Declares an ActivityMapsBinding object
    List<Address> ListGeoCoder; // Declares a List<Address> object to hold addresses returned by the geocoder
    private static final int LOCATION_PERMISSION_CODE = 101; // Declares a static final integer variable LOCATION_PERMISSION_CODE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater()); // Inflates the activity_maps.xml layout using the binding object
        setContentView(binding.getRoot()); // Sets the root view of the layout as the content view for the activity

        if (isLocationPermissionGranted()) { // Checks if location permission is granted
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map); // Finds the map fragment using its ID and casts it to a SupportMapFragment object
            mapFragment.getMapAsync(this); // Calls the getMapAsync() method
        } else {
            requestLocationPermission(); // Requests for location permission
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap; // Assigns the GoogleMap object to mMap

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == getPackageManager().PERMISSION_GRANTED) {
            // Checks if the app has permission to access the device's fine location
            mMap.setMyLocationEnabled(true); // Enables the my location button on the map
        }
    }

    public boolean isLocationPermissionGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Checks if the app has been granted permission to access the device's fine location
            return true; // Returns true if permission is granted
        } else {
            return false; // Returns false if permission is not granted
        }
    }

    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        // Requests for permission to access the device's fine location
    }
}