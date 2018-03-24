package com.example.himanshudahiya.hackathon_cleaniness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Review> arr;
    private ArrayList<LookupModel> lookupTable ;
    private ArrayList<AreaModel> areaModels ;
    private ArrayList <LikedByTable> likedByTables ;
    private Double currentLatitude ;
    private Double currentLongitude ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(this, R.layout.activity_reviews, null);
        lookupTable = new ArrayList<>();
        areaModels = new ArrayList<>();
        likedByTables = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("lookup");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    lookupTable.add(snapshot.getValue(LookupModel.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            // Set item views based on your views and data model

        });

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

        DatabaseReference likeref = FirebaseDatabase.getInstance().getReference("like");
        likeref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    likedByTables.add(snapshot.getValue(LikedByTable.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


            // Set item views based on your views and data model

        });



        for (int i = 0 ; i < lookupTable.size() ; i++){
            String areaPk = lookupTable.get(i).getPk();
            String lat = "1.000" , lon = "1.000" ;
            for (int j = 0 ; j < areaModels.size();j++){
                String pk = areaModels.get(j).getPk() ;
                if (pk.equals(areaPk)){
                    lat = areaModels.get(j).getLatitude();
                    lon = areaModels.get(j).getLongitude();
                    break ;
                }
            }
            Double latitude1 = Double.parseDouble(lat);
            Double longitude1 = Double.parseDouble(lon);
            
            Double distance = CalculationByDistance(latitude1,currentLatitude,longitude1,currentLongitude);
            if (distance < 50){
                Review review = new Review();
                review.areaPk = areaPk ;
                review.reviewPK = lookupTable.get(i).getPk();
                review.Review = lookupTable.get(i).getReviewMsg();
                review.userPk = lookupTable.get(i).getUserPk();

                int count = 0 ;
                for (int k = 0 ; k < likedByTables.size() ; k++ ){
                    if (lookupTable.get(i).getPk().equals(likedByTables.get(k).getMsgPk())){
                        count ++ ;
                    }
                }
                review.no_of_likes = count ;
                arr.add(review);
            }


        }

        mRecyclerView=contentView.findViewById(R.id.recycler);
        arr=new ArrayList<Review>();
        for(int i=0;i<6;i++)
            arr.add(new Review());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReviewAdapter adapter=new ReviewAdapter(this,arr);
        adapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, boolean x) {
                ImageButton img=(ImageButton) view;
                if(x)
                    img.setImageResource(R.drawable.ic_action_name);
                else{
                    img.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                }
            }});
        mRecyclerView.setAdapter(adapter);
        setContentView(contentView);


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
