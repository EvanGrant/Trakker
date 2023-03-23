package com.example.trakker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mIDs = new ArrayList<>();


    private Context mContext;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls, ArrayList<String> ids) {
        mNames = names;
        mImageUrls = imageUrls;
        mContext = context;
        mIDs = ids;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .override(90,100)
                .into(holder.image);


        holder.name.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(holder.getAbsoluteAdapterPosition()) + "ID: " + mIDs.get(holder.getAbsoluteAdapterPosition()));
                Toast.makeText(mContext, mNames.get(holder.getAbsoluteAdapterPosition()), Toast.LENGTH_SHORT).show();

                String passedID = mIDs.get(holder.getAbsoluteAdapterPosition());

                passData(passedID, mContext);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }

    private void passData(String id, Context context) {

        Intent intent = new Intent(context, MovieTVShowDisplayPage.class);
        intent.putExtra("id", id);
        context.startActivity(intent);

    }



}