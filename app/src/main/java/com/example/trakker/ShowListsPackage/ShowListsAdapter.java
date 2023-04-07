package com.example.trakker.ShowListsPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trakker.R;
import com.example.trakker.ShowListsPackage.ListItems;
import com.example.trakker.ShowListsPackage.ShowListsViewHolder;

import java.util.List;

public class ShowListsAdapter extends RecyclerView.Adapter<ShowListsViewHolder> {

    Context context;
    List<ListItems> userLists;

    public ShowListsAdapter (Context context, List<ListItems> userLists){
        this.context = context;
        this.userLists = userLists;
    }

    @NonNull
    @Override
    public ShowListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowListsViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListsViewHolder holder, int position) {

        holder.listTextView.setText(userLists.get(position).getListName());

    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }
}
