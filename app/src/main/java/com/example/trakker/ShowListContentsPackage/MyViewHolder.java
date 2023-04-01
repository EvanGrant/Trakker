package com.example.trakker.ShowListContentsPackage;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trakker.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView,emailView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.posterListContentsImageView);
        nameView = itemView.findViewById(R.id.titleListContentsTextView);
        //emailView = itemView.findViewById(R.id.titleListContentsTextView);
    }
}
