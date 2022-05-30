package com.ComapnyAdvisors.hikeAdvisor;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ComapnyAdvisors.hikeAdvisor.data.Repository;
import com.ComapnyAdvisors.hikeAdvisor.model.Park;
import com.ComapnyAdvisors.hikeAdvisor.model.ParkViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ComapnyAdvisors.hikeAdvisor.databinding.ActivityMapsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ParkViewModel parkViewModel;
    private List<Park> parkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        parkViewModel = new ViewModelProvider(this)
                .get(ParkViewModel.class);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemReselectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();
            if(id == R.id.map_nav_button){
                //Show map view
                mMap.clear();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.map,selectedFragment)
                        .commit();
                mapFragment.getMapAsync(this);
                //Show the map view
            }else if(id == R.id.parks_nav_button){
                selectedFragment = ParksFragment.newInstance();
                // show parks fragment
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.map,selectedFragment)
                    .commit();
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        parkList = new ArrayList<>();
        parkList.clear();


        Repository.getParks(parks -> {
            parkList = parks;
            for (Park park: parks) {
                LatLng sydney = new LatLng(-34, 151);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,5));
                Log.d("Parks", "onMapReady: " + park.getFullName());
            }
            parkViewModel.setSelectedParks(parkList);
        });
    }
}