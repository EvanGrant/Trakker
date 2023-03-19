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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    private MyRecyclerViewAdapter firstAdapter;
    private MyRecyclerViewAdapter adapter2;
    private MyRecyclerViewAdapter adapter3;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        getData();

        // data to populate the RecyclerView with
        ArrayList<Integer> viewTopRatedMovies = new ArrayList<>();
        viewTopRatedMovies.add(Color.BLUE);
        viewTopRatedMovies.add(Color.YELLOW);
        viewTopRatedMovies.add(Color.MAGENTA);
        viewTopRatedMovies.add(Color.RED);
        viewTopRatedMovies.add(Color.BLACK);

        ArrayList<String> movieNames = new ArrayList<>();
        movieNames.add("Horse");
        movieNames.add("Cow");
        movieNames.add("Camel");
        movieNames.add("Sheep");
        movieNames.add("Goat");


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainPage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        firstAdapter = new MyRecyclerViewAdapter(this, viewTopRatedMovies, movieNames);
        firstAdapter.setClickListener(this);
        recyclerView.setAdapter(firstAdapter);

        /*

        // set up the RecyclerView
        RecyclerView recyclerView2 = findViewById(R.id.rvAnimals2);
        LinearLayoutManager horizontalLayoutManager2
                = new LinearLayoutManager(MainPage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);
        adapter2 = new MyRecyclerViewAdapter(this, viewTopRatedMovies, movieNames);
        adapter2.setClickListener(this);
        recyclerView2.setAdapter(adapter2);

        // set up the RecyclerView
        RecyclerView recyclerView3 = findViewById(R.id.rvAnimals3);
        LinearLayoutManager horizontalLayoutManager3
                = new LinearLayoutManager(MainPage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(horizontalLayoutManager3);
        adapter3 = new MyRecyclerViewAdapter(this, viewTopRatedMovies, movieNames);
        adapter3.setClickListener(this);
        recyclerView3.setAdapter(adapter3);

        */

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

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + firstAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
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


