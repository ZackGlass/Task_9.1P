package com.example.lostandfoundapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.lostandfoundapp.data.DatabaseHelper;
import com.example.lostandfoundapp.model.Advert;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.lostandfoundapp.databinding.ActivityMapBinding;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        List<Advert> allAds = db.fetchAllAds();

        if (!allAds.isEmpty())
        {
            for (int i = 0; i < allAds.size(); i++)
            {
                LatLng marker = new LatLng(Double.parseDouble(allAds.get(i).getLat()), Double.parseDouble(allAds.get(i).getLng()));
                mMap.addMarker(new MarkerOptions().position(marker).title(allAds.get(i).getAdName()));
            }
        }
        LatLng melbourne = new LatLng(-37.81806760638826, 144.96708324356032);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(melbourne, 8));
    }
}