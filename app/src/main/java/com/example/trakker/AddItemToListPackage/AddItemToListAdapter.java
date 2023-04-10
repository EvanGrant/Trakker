package com.example.trakker.AddItemToListPackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.GlobalClass;
import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.R;
import com.example.trakker.Registration;
import com.example.trakker.ShowListContentsPackage.ShowListContentsPage;
import com.example.trakker.ShowListsPackage.ListItems;
import com.example.trakker.ShowListsPackage.ShowListsPage;
import com.example.trakker.ShowListsPackage.ShowListsViewHolder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class AddItemToListAdapter extends RecyclerView.Adapter<AddItemToListViewHolder> {

    GlobalClass globalClass = new GlobalClass();
    Context mContext;
    List<ListItems> userLists;

    int passedMediaID;
    String passedMediaType;
    String passMediaName;
    String passedMediaPosterURL;


    private static final String TAG = "ShowListsAdapter";

    public AddItemToListAdapter (Context context, List<ListItems> userLists, int passedMediaID, String passedMediaType, String passMediaName, String passedMediaPosterURL){
        this.mContext = context;
        this.userLists = userLists;
        this.passedMediaID = passedMediaID;
        this.passedMediaPosterURL = passedMediaPosterURL;
        this.passedMediaType = passedMediaType;
        this.passMediaName = passMediaName;
    }

    @NonNull
    @Override
    public AddItemToListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddItemToListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rv_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddItemToListViewHolder holder, int position) {

        ListItems model = userLists.get(position);

        holder.listTextView.setText(userLists.get(position).getListName());

        holder.listTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "Clicked on: " + model.getListID());
                Log.d(TAG, "Clicked on: " + model.getListName());
                Toast.makeText(mContext, model.getListID() + " " + model.getListName(), Toast.LENGTH_SHORT).show();

                //int passedID = model.getListID();

                RegisterItemToList(model.getListID(), view);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    public void RegisterItemToList(int listID, View v){

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "http://10.0.2.2:4000/listsToMovies/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(mContext, "Added to List!", Toast.LENGTH_SHORT).show();

                        try {

                            JSONObject returnObject = new JSONObject(response);

                            int userID = Integer.parseInt(returnObject.getString("id"));

                            if(userID > 0){

                                Intent intent = new Intent(v.getContext(), MainPage.class);

                                v.getContext().startActivity(intent);

                            }else{

                                Toast.makeText(mContext, "Invalid Addition to List", Toast.LENGTH_SHORT).show();

                            }


                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        //go to the next page and add userId as a variable

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "list is already in database", Toast.LENGTH_SHORT).show();
            }
        }){

            protected Map<String, String> getParams(){

                Map<String, String> paramV = new HashMap<>();
                paramV.put("ListId", String.valueOf(listID));
                paramV.put("MovieId", String.valueOf(passedMediaID));
                paramV.put("MediaType", passedMediaType);
                paramV.put("posterURL", passedMediaPosterURL);

                return paramV;

            }
        };

        queue.add(stringRequest);

    }

}
