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

import java.util.ArrayList;
import java.util.List;

public class Cleaner extends AppCompatActivity {

    ArrayList<CleanDrive> cleandrive;
    int GO_TO_NGO=0,FIND_AREA=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout layout=(RelativeLayout) View.inflate(this,R.layout.activity_cleaner,null);
        RecyclerView rvClean =new RecyclerView(this);
        rvClean.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cleandrive=new ArrayList<CleanDrive>();
        for(int i=0;i<20;i++)
            cleandrive.add(new CleanDrive(" "+i));

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
}
