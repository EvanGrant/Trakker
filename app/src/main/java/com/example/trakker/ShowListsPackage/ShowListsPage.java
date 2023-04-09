package com.example.trakker.ShowListsPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.util.LogTime;
import com.example.trakker.GlobalClass;
import com.example.trakker.MainPagePackage.MyRecyclerViewAdapter;
import com.example.trakker.R;
import com.example.trakker.ShowListContentsPackage.Item;
import com.example.trakker.ShowListContentsPackage.MyAdapter;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;

public class ShowListsPage extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    RecyclerView recyclerView;
    ShowListsAdapter adapter;

    GlobalClass g = new GlobalClass();

    List<ListItems> listNames = new ArrayList<ListItems>();

    private static final String TAG = "ShowListsPage";

    Context context = this;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists_page);

        //To Test if RecyclerView Works
        //listNames.add(new ListItems("1st list", 2));
        //listNames.add(new ListItems("other list", 3));
        //listNames.add(new ListItems("another list", 4));

        try {
            run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        recyclerView = findViewById(R.id.rvShowLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShowListsAdapter(getApplicationContext(), listNames);
        recyclerView.setAdapter(adapter);


    }


    public void run() throws Exception{

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://10.0.2.2:4000/lists/" + g.getUserID())
                .build();

        Callback callback = new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String myResponse = response.body().string();

                try {

                    JSONArray jsonArray = new JSONArray(myResponse);


                        for (int i = 0; i < jsonArray.length(); i++)
                        {

                            JSONObject list = jsonArray.getJSONObject(i);

                            int listID = list.getInt("id");
                            String listName = list.getString("ListName");

                            //Info is getting in correctly, but because its async its not being filled into the recyclerview correctly
                            listNames.add(new ListItems(listName, listID));

                        }



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                ShowListsPage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, myResponse, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };

        client.newCall(request).enqueue(callback);

    }

    public void GetListNames(int userID){

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


        //initRecyclerView();

    }

    private void initRecyclerView(){




        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        //recyclerView.setLayoutManager(layoutManager);
        //MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, topRatedMovieTitleArray, topRatedMoviePosterArray, topRatedMoviesIDArray, passedUserID);
        //recyclerView.setAdapter(adapter);


    }

    private class LongRunningTask extends AsyncTask<Void, Void, List<ListItems>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected List<ListItems> doInBackground(Void... voids) {


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
        protected void onPostExecute(List<ListItems> list) {
            super.onPostExecute(list);



            adapter.notifyDataSetChanged();

            Log.d(TAG, "onPostExecute: UI UPDATE");

        }
    }

}