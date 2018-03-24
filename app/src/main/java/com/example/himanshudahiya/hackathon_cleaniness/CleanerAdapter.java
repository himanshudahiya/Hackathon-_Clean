package com.example.himanshudahiya.hackathon_cleaniness;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sachin on 24/03/18.
 */

public class CleanerAdapter extends RecyclerView.Adapter<CleanerAdapter.ViewHolder> {

    private List<CleanDrive> mCleanDrive;
    private Context mContext;
    private OnItemClickListener listener;
    int GO_TO_NGO=0;
    int FIND_AREA=1;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position,int code);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CleanerAdapter(Context context,List<CleanDrive> cleanDrive){
        mContext=context;
        mCleanDrive=cleanDrive;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_cleandrive, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CleanerAdapter.ViewHolder viewHolder, int position) {
        CleanDrive cleandrive = mCleanDrive.get(position);

        // Set item views based on your views and data model
        TextView area,ngo,time,volunteers,perks;
        area=viewHolder.area;
        ngo=viewHolder.ngo;
        time=viewHolder.time;
        volunteers=viewHolder.volunteers;
        perks=viewHolder.perks;
        area.setText(cleandrive.Area);
        ngo.setText(cleandrive.NGO);
        time.setText("Time: "+cleandrive.time+" Date: "+cleandrive.date);
        volunteers.setText("NO OF VOLUNTEERS "+cleandrive.NO_OF_VOLUNTEERS);
        perks.setText(cleandrive.PERKS);

         }

    @Override
    public int getItemCount() {
        return 20;
    }

    // Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
public class ViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public TextView area;
    public TextView ngo;
    public TextView time;
    public TextView volunteers;
    public TextView perks;
    public Button GoToNGO,FindArea;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);
        area=itemView.findViewById(R.id.Area);
        ngo=itemView.findViewById(R.id.ngoname);
        time=itemView.findViewById(R.id.time);
        volunteers=itemView.findViewById(R.id.no_volunteers);
        perks=itemView.findViewById(R.id.perks);
        GoToNGO=itemView.findViewById(R.id.gotongo);
        FindArea=itemView.findViewById(R.id.findarea);
        GoToNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(GoToNGO, position,GO_TO_NGO);
                    }
                }
            }
        });

        FindArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(FindArea, position,FIND_AREA);
                    }
                }
            }
        });

    }
}
}