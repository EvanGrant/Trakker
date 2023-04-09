package com.example.trakker.ShowListsPackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trakker.MovieTVShowDisplayPage;
import com.example.trakker.R;
import com.example.trakker.ShowListContentsPackage.ShowListContentsPage;
import com.example.trakker.ShowListsPackage.ListItems;
import com.example.trakker.ShowListsPackage.ShowListsViewHolder;

import java.util.List;

public class ShowListsAdapter extends RecyclerView.Adapter<ShowListsViewHolder> {

    Context mContext;
    List<ListItems> userLists;

    private static final String TAG = "ShowListsAdapter";

    public ShowListsAdapter (Context context, List<ListItems> userLists){
        this.mContext = context;
        this.userLists = userLists;
    }

    @NonNull
    @Override
    public ShowListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowListsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListsViewHolder holder, int position) {

        ListItems model = userLists.get(position);

        holder.listTextView.setText(userLists.get(position).getListName());

        holder.listTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "Clicked on: " + model.getListID());
                Log.d(TAG, "Clicked on: " + model.getListName());
                Toast.makeText(mContext, model.getListID() + " " + model.getListName(), Toast.LENGTH_SHORT).show();

                int passedID = model.getListID();

                passData(passedID, mContext);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    private void passData(int id, Context context) {

        Intent intent = new Intent(context, ShowListContentsPage.class);
        intent.putExtra("listid", id);
        context.startActivity(intent);



    }

}
