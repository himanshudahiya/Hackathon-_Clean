package com.example.himanshudahiya.hackathon_cleaniness;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    int Lattitude,Longitude;
    PlaceAutocompleteFragment placeAutoComplete;


    private DatabaseReference areadata ;
    private DatabaseReference databaseLook ;
    private DatabaseReference databaseLike ;
    private DatabaseReference databaseNgo ;
    private DatabaseReference databaseEvent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);

        areadata = FirebaseDatabase.getInstance().getReference("areas");
        String id = areadata.push().getKey();
        AreaModel area = new AreaModel(id,"76.53","30.97") ;
        areadata.child(id).setValue(area);


        databaseLook = FirebaseDatabase.getInstance().getReference("lookup");
        databaseLike = FirebaseDatabase.getInstance().getReference("like");
        databaseNgo = FirebaseDatabase.getInstance().getReference("ngo");
        databaseEvent = FirebaseDatabase.getInstance().getReference("event");



        addData();

        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place selected: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Lattitude=12;
        Longitude=12;
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
        LatLng sydney = new LatLng(Lattitude, Longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    private void addData(){


//        String id2 = databaseArea.push().getKey();
//        AreaModel user2 = new AreaModel(id2,"76.53","34.97") ;
//        databaseArea.child(id2).setValue(user2);
//
//        String id3 = databaseArea.push().getKey();
//        AreaModel user3 = new AreaModel(id3,"85.53","30.97") ;
//        databaseArea.child(id3).setValue(user3);
//        Toast.makeText(this , "Bedsore added" , Toast.LENGTH_LONG).show();









    }
}
