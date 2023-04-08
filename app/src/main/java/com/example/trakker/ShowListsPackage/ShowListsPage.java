package com.example.trakker.ShowListsPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.util.LogTime;
import com.example.trakker.GlobalClass;
import com.example.trakker.R;
import com.example.trakker.ShowListContentsPackage.Item;
import com.example.trakker.ShowListContentsPackage.MyAdapter;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowListsPage extends AppCompatActivity {

    RecyclerView recyclerView;
    //ShowListsAdapter adapter;

    GlobalClass g = new GlobalClass();

    List<ListItems> listNames = new ArrayList<ListItems>();

    private static final String TAG = "ShowListsPage";

    Context context = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists_page);



        new LongRunningTask().execute();

        //To Test if RecyclerView Works
        //listNames.add(new ListItems("1st list", 2));

        //adapter = new ShowListsAdapter(getApplicationContext(), listNames);
        recyclerView = findViewById(R.id.rvShowLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ShowListsAdapter(getApplicationContext(),listNames));


        //int userID = (g.getUserID());
        //GetListNames(userID);



    }

    private class LongRunningTask extends AsyncTask<Void, Void, Void> {

        /*

        private Context mContext;

        public LongRunningTask(Context context){
            mContext = context;
        }

        */

        @Override
        protected Void doInBackground(Void... voids) {

            //MYSQL CALL HERE
            Log.d(TAG, "doInBackground: MySQL Call");

            RequestQueue mRequestQueue;
            StringRequest mStringRequest;

            // RequestQueue initialized
            mRequestQueue = Volley.newRequestQueue(context);

            String RESTUrl = "http://10.0.2.2:4000/lists/" + g.getUserID();

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

                                int listID = list.getInt("id");
                                String listName = list.getString("ListName");

                                //Info is getting in correctly, but because its async its not being filled into the recyclerview correctly
                                listNames.add(new ListItems(listName, listID));

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

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            //ShowListsAdapter.notifyDataSetChanged();

            Log.d(TAG, "onPostExecute: UI UPDATE");

        }
    }


    public void GetListNames(int userID){

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

                            int listID = list.getInt("id");
                            String listName = list.getString("ListName");

                            //Info is getting in correctly, but because its async its not being filled into the recyclerview correctly
                            listNames.add(new ListItems(listName, listID));

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