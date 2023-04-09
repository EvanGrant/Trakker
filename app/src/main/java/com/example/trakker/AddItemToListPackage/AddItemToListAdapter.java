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
import com.example.trakker.ShowListsPackage.ShowListsViewHolder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddItemToListAdapter extends RecyclerView.Adapter<AddItemToListViewHolder> {

    GlobalClass globalClass = new GlobalClass();
    Context mContext;
    List<ListItems> userLists;

    private static final String TAG = "ShowListsAdapter";

    public AddItemToListAdapter (Context context, List<ListItems> userLists){
        this.mContext = context;
        this.userLists = userLists;
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

                //passData(passedID, mContext);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userLists.size();
    }

    public void RegisterUser(View v){

        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "http://10.0.2.2:4000/users/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(mContext, "Added Item to List!", Toast.LENGTH_SHORT).show();

                        try {

                            JSONObject returnObject = new JSONObject(response);

                            int userID = Integer.parseInt(returnObject.getString("id"));


                            if(userID > 0){

                                String eMail = returnObject.getString("Email");
                                String firstname = returnObject.getString("FirstName");
                                String lastname = returnObject.getString("LastName");

                                Intent intent = new Intent(v.getContext(), MainPage.class);

                                globalClass.setUserID(userID);
                                globalClass.setUserEmail(eMail);
                                globalClass.setUserFirstName(firstname);
                                globalClass.setUserLastName(lastname);

                                //intent.putExtra("userID", userID);
                                //intent.putExtra("firstname", fName);

                                v.getContext().startActivity(intent);

                            }else{

                                Toast.makeText(mContext, "Could not add item to list", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(mContext, "user is already in database", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){


                Map<String, String> paramV = new HashMap<>();
                /*
                paramV.put("FirstName", fName);
                paramV.put("LastName", lName);
                paramV.put("Email", email);
                paramV.put("Password", password);
                */

                return paramV;

            }
        };

        queue.add(stringRequest);

    }

}
