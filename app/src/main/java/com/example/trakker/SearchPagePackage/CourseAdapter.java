package com.example.trakker.SearchPagePackage;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trakker.MovieTVShowDisplayPage;
import com.example.trakker.R;

import java.util.ArrayList;

//FOR SEARCH PAGE RECYCLERVIEW

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private ArrayList<CourseModel> courseModelArrayList;

    private static final String TAG = "CourseAdapter";
    private Context mContext;
    public CourseAdapter(ArrayList<CourseModel> courseModelArrayList, Context context) {
        this.courseModelArrayList = courseModelArrayList;
        mContext = context;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<CourseModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        courseModelArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        CourseModel model = courseModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCourseName());
        holder.courseDescTV.setText(model.getCourseDescription());

        Glide.with(mContext)
                .asBitmap()
                .load(model.getPoster())
                .override(90,100)
                .into(holder.coursePosterTV);

        holder.coursePosterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Log.d(TAG, "onClick: clicked on an image: " + courseModelArrayList.get(holder.getAbsoluteAdapterPosition()) + "ID: " + mIDs.get(holder.getAbsoluteAdapterPosition()));
                //Toast.makeText(mContext, holder.courseNameTV.get(holder.getAbsoluteAdapterPosition()), Toast.LENGTH_SHORT).show();

                Log.d(TAG, "Clicked on: " + courseModelArrayList.get(holder.getAbsoluteAdapterPosition()));
                Log.d(TAG, "Clicked on: " + model.getCourseName());

                String passedID = model.getCourseID();

                passData(passedID, mContext);

            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private final TextView courseNameTV;
        private final TextView courseDescTV;
        private final ImageView coursePosterTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            coursePosterTV = itemView.findViewById(R.id.idTVPosterImageView);
        }
    }

    private void passData(String id, Context context) {

        Intent intent = new Intent(context, MovieTVShowDisplayPage.class);
        intent.putExtra("id", id);
        context.startActivity(intent);

    }

}
