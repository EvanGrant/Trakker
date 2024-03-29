package com.example.trakker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.MainPagePackage.MainPage;

import org.json.JSONArray;
import org.json.JSONObject;




public class ListPage extends AppCompatActivity {

    public int userID;

    ArrayList<String> Lists = new ArrayList<String>();

    //ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_list_layout);

    //String UserList[]= {"Favorite Movie", "Favorite Show", "Action Movie", "Comedy Movies"};


    String[] UserList = {"Fav Mov"};
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_page);

        GetLists(2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_layout, R.id.Listtext, Lists);

        listView = (ListView) findViewById(R.id.Listpage);

        arrayAdapter.clear();

        arrayAdapter.notifyDataSetChanged();

        arrayAdapter.addAll(Lists);

        arrayAdapter.notifyDataSetChanged();

        listView.setAdapter(arrayAdapter);



        //Lists.add("fav mov");

    }

    public void GetLists(int userID){

        RequestQueue mRequestQueue;
        StringRequest mStringRequest;

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        String RESTUrl = "http://10.0.2.2:4000/lists/" + userID;

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, RESTUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    if (jsonArray.length()>0)
                    {
                        for (int i = 0; i <= jsonArray.length(); i++)
                        {

                            JSONObject list = jsonArray.getJSONObject(i);

                            String listName = list.getString("ListName");

                            Lists.add(listName);



                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("Volley Response", "Error : Volley Request did not work" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);


    }



}

