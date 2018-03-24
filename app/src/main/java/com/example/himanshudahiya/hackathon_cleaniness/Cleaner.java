package com.example.himanshudahiya.hackathon_cleaniness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cleaner extends AppCompatActivity {

    ArrayList<EventDetails> eventDetails ;
    ArrayList<CleanDrive> cleandrive;
    ArrayList<NgoDetails> ngoDetails ;
    private ArrayList<AreaModel> areaModels ;
    private double currentLatitude ;
    private double currentLongitude ;
    int GO_TO_NGO=0,FIND_AREA=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout layout=(RelativeLayout) View.inflate(this,R.layout.activity_cleaner,null);
        RecyclerView rvClean =new RecyclerView(this);
        rvClean.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cleandrive=new ArrayList<CleanDrive>();
        eventDetails = new ArrayList<>();
        areaModels = new ArrayList<>();
        ngoDetails = new ArrayList<>();

        DatabaseReference areaRef = FirebaseDatabase.getInstance().getReference("area");
        areaRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    areaModels.add(snapshot.getValue(AreaModel.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            // Set item views based on your views and data model
        });

        DatabaseReference eventref = FirebaseDatabase.getInstance().getReference("event");
        eventref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    eventDetails.add(snapshot.getValue(EventDetails.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            // Set item views based on your views and data model
        });


        DatabaseReference ngoRef = FirebaseDatabase.getInstance().getReference("ngo");
        ngoRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ngoDetails.add(snapshot.getValue(NgoDetails.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            // Set item views based on your views and data model
        });

        for (int i = 0 ; i < eventDetails.size() ; i++){
            String ngoPk = eventDetails.get(i).getPk();
            String lat = "1.000" , lon = "1.000" ;
            String ngoName = "Clean India" ;
            for (int j = 0 ; j < ngoDetails.size();j++){
                String pk = ngoDetails.get(j).getPk() ;
                if (pk.equals(ngoPk)){
                    lat = ngoDetails.get(j).getLatitude();
                    lon = ngoDetails.get(j).getLongitude();
                    ngoName = ngoDetails.get(j).getNgo_name();
                    break ;
                }
            }
            Double latitude1 = Double.parseDouble(lat);
            Double longitude1 = Double.parseDouble(lon);

            Double distance = CalculationByDistance(latitude1,currentLatitude,longitude1,currentLongitude);
            if (distance < 50){
                CleanDrive clean = new CleanDrive();
                clean.date = eventDetails.get(i).getDate() ;
                clean.time = eventDetails.get(i).getTimestamp();
                clean.PERKS =  eventDetails.get(i).getPerks();
                clean.NGO = ngoName ;
                Random rand = new Random();

                // Generate random integers in range 0 to 999
                int rand_int1 = rand.nextInt(10);
                clean.NO_OF_VOLUNTEERS = rand_int1 ;

                cleandrive.add(clean);
            }


        }


        // Create adapter passing in the sample user data
        CleanerAdapter adapter = new CleanerAdapter(this, cleandrive);
        // Attach the adapter to the recyclerview to populate items
        rvClean.setAdapter(adapter);
        // Set layout manager to position the items
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT
                ,RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(rvClean,params);
           setContentView(layout);
        adapter.setOnItemClickListener(new CleanerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position,int code) {
                if(code==GO_TO_NGO)
                {

                    Toast.makeText(Cleaner.this,  "Go to ngo of "+cleandrive.get(position).Area+ " was clicked!", Toast.LENGTH_SHORT).show();
                }
                if(code==FIND_AREA)
                {
                    Intent i=new Intent(getApplicationContext(),LocationActivity.class);
                    i.putExtra("lattitude",12);
                    i.putExtra("longitude",12);
                    startActivity(i);
                    //Toast.makeText(Cleaner.this,  "Go to area of "+cleandrive.get(position).Area+ " was clicked!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public double CalculationByDistance(double lat1 , double lat2 , double lon1 , double lon2) {
        int Radius = 6371;// radius of earth in Km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }
}
