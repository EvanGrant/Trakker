package com.example.trakker.AddItemToListPackage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trakker.R;

public class AddItemToListViewHolder extends RecyclerView.ViewHolder {

    TextView listTextView;

    public AddItemToListViewHolder(@NonNull View itemView) {
        super(itemView);

        listTextView = itemView.findViewById(R.id.ListNameTextView);

    }

}
