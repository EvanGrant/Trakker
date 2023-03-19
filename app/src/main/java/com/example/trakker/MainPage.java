package com.example.trakker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainPage extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&page=1";
    private static final String TAG = "MainPage";
    //vars
    private ArrayList<String> movieTitleArray = new ArrayList<>();
    private ArrayList<String> moviePosterArray = new ArrayList<>();
    private ArrayList<String> movieIDArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        getData();

        // Initialize and assign bottom navigation view
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.MainPage);

        // Perform item selected listener for bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.UserAccount:
                        startActivity(new Intent(getApplicationContext(),UserAccountPage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.MainPage:
                        return true;

                    case R.id.Search:
                        startActivity(new Intent(getApplicationContext(),SearchPage.class));
                        overridePendingTransition(0,0);
                        return true;


                }

                return false;

            }
        });
    }

    private void getData() {

        //Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        /*
        moviePosterArray.add("https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg");
        movieTitleArray.add("Havasu Falls");
        */

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

                Log.e("Res: ", response);
                try
                {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    if (jsonArray.length()>0)
                    {
                        for (int i = 0; i < jsonArray.length(); i++)
                        {

                            JSONObject movie = jsonArray.getJSONObject(i);

                            String movieID = movie.getString("id");
                            String movieTitle = movie.getString("original_title");
                            String moviePoster = movie.getString("poster_path");

                            movieIDArray.add(movieID);
                            movieTitleArray.add(movieTitle);
                            moviePosterArray.add("https://image.tmdb.org/t/p/w500/" + moviePoster);

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



        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, movieTitleArray, moviePosterArray);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView2 = findViewById(R.id.rvAnimals2);
        recyclerView2.setLayoutManager(layoutManager2);
        MyRecyclerViewAdapter adapter2 = new MyRecyclerViewAdapter(this, movieTitleArray, moviePosterArray);
        recyclerView2.setAdapter(adapter2);
    }
}



