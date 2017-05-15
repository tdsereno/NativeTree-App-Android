package com.example.tiago.anative;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.tiago.anative.Controle.Util;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<String> dadosMapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if (params != null) {
            //Util.exibeMensagem("Parans ok", getApplicationContext());
            dadosMapa = params.getStringArrayList("dados");
        } else dadosMapa = new ArrayList<String>();
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

        // Add a marker in Sydney and move the camera
        if (dadosMapa != null) {
            for (int i = 0; i < dadosMapa.size() - 2; i = i + 3) {
                try {
                    double latitude = Double.parseDouble(dadosMapa.get(i));
                    double longitude = Double.parseDouble(dadosMapa.get(i + 1));
                    String obs = (dadosMapa.get(i + 2));
                    LatLng pontomapa = new LatLng(latitude, longitude);


                    mMap.addMarker(new MarkerOptions().position(pontomapa).title(obs));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(pontomapa));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pontomapa, 15));

                } catch (Exception ex) {

                }


            }
        }
    }
}
