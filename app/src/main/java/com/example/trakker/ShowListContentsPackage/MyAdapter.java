package com.example.trakker.ShowListContentsPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trakker.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_list_contents,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        //holder.courseNameTV.setText(model.getCourseName());
        //holder.emailView.setText(items.get(position).getEmail());
        //holder.imageView.setImageResource(items.get(position).getImage());

        Glide.with(context)
                .asBitmap()
                .load(items.get(position).getImage())
                .override(90,100)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
