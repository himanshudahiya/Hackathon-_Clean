package com.example.himanshudahiya.hackathon_cleaniness;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sachin on 24/03/18.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> mReview;
    private Context mContext;
    private OnItemClickListener listener;
    private ArrayList<LikedByTable> likeTable ;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView,boolean x);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ReviewAdapter(Context context, List<Review> review){
        mContext=context;
        mReview=review;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_review, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder viewHolder, int position) {

        TextView mreview, numberoflikes;
        ImageButton img;
        mreview = viewHolder.mreview;
        numberoflikes = viewHolder.numberoflikes;
        img = viewHolder.img;
        mreview.setText(mReview.get(position).Review);
        likeTable = new ArrayList<>();
        numberoflikes.setText(" "+mReview.get(position). get_no_of_likes());

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    // Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
public class ViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    TextView mreview;
    TextView numberoflikes;
    ImageButton img;
    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ViewHolder(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);
        mreview=itemView.findViewById(R.id.text_recycler);
        numberoflikes=itemView.findViewById(R.id.no_of_likes);
        img=itemView.findViewById(R.id.like_button);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        img.setMaxHeight(img.getHeight());
                        Review review=mReview.get(position);
                        review.liked=!review.liked;
                        listener.onItemClick(img,review.liked);
                        numberoflikes.setText(" "+mReview.get(position).get_no_of_likes());

                    }
                }
            }
        });
//        area=itemView.findViewById(R.id.Area);
//        ngo=itemView.findViewById(R.id.ngoname);
//        time=itemView.findViewById(R.id.time);
//        volunteers=itemView.findViewById(R.id.no_volunteers);
//        perks=itemView.findViewById(R.id.perks);
//        GoToNGO=itemView.findViewById(R.id.gotongo);
//        FindArea=itemView.findViewById(R.id.findarea);
//        GoToNGO.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Triggers click upwards to the adapter on click
//                if (listener != null) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        listener.onItemClick(GoToNGO, position,GO_TO_NGO);
//                    }
//                }
//            }
//        });
//
//        FindArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Triggers click upwards to the adapter on click
//                if (listener != null) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        listener.onItemClick(FindArea, position,FIND_AREA);
//                    }
//                }
//            }
//        });

    }
}
}