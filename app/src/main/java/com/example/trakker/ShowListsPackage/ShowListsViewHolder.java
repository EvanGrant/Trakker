package com.example.trakker.ShowListsPackage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trakker.R;

public class ShowListsViewHolder extends RecyclerView.ViewHolder {

    TextView listTextView;

    public ShowListsViewHolder(@NonNull View itemView) {
        super(itemView);

        listTextView = itemView.findViewById(R.id.ListNameTextView);

    }
}
