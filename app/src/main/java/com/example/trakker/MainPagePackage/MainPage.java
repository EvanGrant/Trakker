package com.example.trakker.MainPagePackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.GlobalClass;
import com.example.trakker.ListPage;
import com.example.trakker.R;
import com.example.trakker.SearchPagePackage.SearchPage;
import com.example.trakker.ShowListsPackage.ShowListsPage;
import com.example.trakker.UserAccountPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainPage extends AppCompatActivity {

    private static final String TAG = "MainPage";

    GlobalClass g = new GlobalClass();

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private Button ListsButton;

    private int passedUserID;

    private String passedFirstName;

    private String topRatedMoviesUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&page=1";
    private ArrayList<String> topRatedMovieTitleArray = new ArrayList<>();
    private ArrayList<String> topRatedMoviePosterArray = new ArrayList<>();
    private ArrayList<String> topRatedMoviesIDArray = new ArrayList<>();


    private String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&page=1";
    private ArrayList<String> popularMovieTitleArray = new ArrayList<>();
    private ArrayList<String> popularMoviePosterArray = new ArrayList<>();
    private ArrayList<String> popularMoviesIDArray = new ArrayList<>();


    private String newReleasesMoviesUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&page=1";
    private ArrayList<String> newReleasesMovieTitleArray = new ArrayList<>();
    private ArrayList<String> newReleasesMoviePosterArray = new ArrayList<>();
    private ArrayList<String> newReleasesMoviesIDArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        TextView welcomeTextBox = findViewById(R.id.welcomeTextView);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("userID", 0);
        passedFirstName = intent.getStringExtra("firstname");

        passedUserID = g.getUserID();

        Toast.makeText(this, "User: " + passedUserID, Toast.LENGTH_SHORT).show();

        welcomeTextBox.setText("Welcome " + passedFirstName);

        getDataTopRatedMovies();
        getDataPopularMovies();
        getNewReleasesMovies();

        // Initialize and assign bottom navigation view
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.MainPage);

        ListsButton = findViewById(R.id.myListsButton);

        ListsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowListsPage.class);
                view.getContext().startActivity(intent);
            }
        });

        // Perform item selected listener for bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.UserAccount:
                        startActivity(new Intent(getApplicationContext(), UserAccountPage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.MainPage:
                        return true;

                    case R.id.Search:
                        startActivity(new Intent(getApplicationContext(), SearchPage.class));
                        overridePendingTransition(0,0);
                        return true;


                }

                return false;

            }
        });
    }

    private void getDataTopRatedMovies() {

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, topRatedMoviesUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                //Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
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

                            topRatedMoviesIDArray.add(movieID);
                            topRatedMovieTitleArray.add(movieTitle);
                            topRatedMoviePosterArray.add("https://image.tmdb.org/t/p/w500/" + moviePoster);

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

    private void getDataPopularMovies() {

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, popularMoviesUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                //Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
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

                            popularMoviesIDArray.add(movieID);
                            popularMovieTitleArray.add(movieTitle);
                            popularMoviePosterArray.add("https://image.tmdb.org/t/p/w500/" + moviePoster);

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

    private void getNewReleasesMovies() {

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, newReleasesMoviesUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                //Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
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

                            newReleasesMoviesIDArray.add(movieID);
                            newReleasesMovieTitleArray.add(movieTitle);
                            newReleasesMoviePosterArray.add("https://image.tmdb.org/t/p/w500/" + moviePoster);

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

        //Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, topRatedMovieTitleArray, topRatedMoviePosterArray, topRatedMoviesIDArray, passedUserID);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView2 = findViewById(R.id.rvAnimals2);
        recyclerView2.setLayoutManager(layoutManager2);
        MyRecyclerViewAdapter adapter2 = new MyRecyclerViewAdapter(this, popularMovieTitleArray, popularMoviePosterArray, popularMoviesIDArray, passedUserID);
        recyclerView2.setAdapter(adapter2);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView3 = findViewById(R.id.rvAnimals3);
        recyclerView3.setLayoutManager(layoutManager3);
        MyRecyclerViewAdapter adapter3 = new MyRecyclerViewAdapter(this, newReleasesMovieTitleArray, newReleasesMoviePosterArray, newReleasesMoviesIDArray, passedUserID);
        recyclerView3.setAdapter(adapter3);
    }




}



