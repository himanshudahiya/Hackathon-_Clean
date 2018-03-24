package com.example.himanshudahiya.hackathon_cleaniness;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by himanshudahiya on 24/03/18.
 */

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private RatingBar mRatingBarValue;
    private RecyclerView mRecyclerView;
    private HorizontalBarChart barChart;
    private Button ratingButton;
    private ArrayList<String> arr;
    private double toSubmitRating = 0;

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_fragment, null);

        mRatingBarValue = (RatingBar) contentView.findViewById(R.id.area_rating);
        final double rating = 2.5;
        mRatingBarValue.setRating(Float.parseFloat(rating + ""));
        dialog.setContentView(contentView);
        barChart = (HorizontalBarChart) contentView.findViewById(R.id.area_rating_count);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(50, 0));
        entries.add(new BarEntry(60, 1));
        entries.add(new BarEntry(40, 2));
        entries.add(new BarEntry(10, 3));
        entries.add(new BarEntry(50, 4));

        BarDataSet bardataset = new BarDataSet(entries, "No of users");
        BarData data = new BarData(labels, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setDescription("");
        barChart.setData(data); // set the data and list of labels into chart

        ratingButton = contentView.findViewById(R.id.rate_button);
        ratingButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            public void onClick(View view){
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.rating_popup, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
//                popupView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        popupWindow.dismiss();
//                        return true;
//                    }
//                });
                final RatingBar areaRatingBar = popupView.findViewById(R.id.rate_area_bar);
                areaRatingBar.setOnTouchListener(new View.OnTouchListener() {
                    double rating1 = 0;
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            float touchPositionX = event.getX();
                            float width = areaRatingBar.getWidth();
                            float starsf = (touchPositionX / width) * 5.0f;
                            int stars = (int)starsf + 1;
                            areaRatingBar.setRating(stars);
                            rating1 = stars;
                            toSubmitRating = rating1;
                            //Toast.makeText(getActivity(), String.valueOf("test"), Toast.LENGTH_SHORT).show();
                            v.setPressed(false);
                        }
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            v.setPressed(true);
                        }

                        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                            v.setPressed(false);
                        }
                        return true;
                    }
                });
                // to get text written in review
                EditText areaReviewText = popupView.findViewById(R.id.review_area_text);
                Button submitButton = popupView.findViewById(R.id.submit_rating);
                submitButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view1){
                        // submit rating to server
                        System.out.println("rating = " + toSubmitRating);
                        popupWindow.dismiss();
                    }
                });
                Button cancelButton = popupView.findViewById(R.id.cancel_rating);
                cancelButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view1){
                        popupWindow.dismiss();
                    }
                });
            }
        });
        mRecyclerView=contentView.findViewById(R.id.recycler);
        arr=new ArrayList<String>();
        for(int i=0;i<20;i++)
            arr.add("hello world fsdkdksj d "+i);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReviewAdapter adapter=new ReviewAdapter(getContext(),arr);
        adapter.setOnItemClickListener(new ReviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                ImageButton img=(ImageButton) view;
                img.setImageResource(R.drawable.ic_action_name);
            }});
        mRecyclerView.setAdapter(adapter);


    }
}
