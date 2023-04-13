package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.trakker.AddItemToListPackage.AddItemToListPage;
import com.example.trakker.MainPagePackage.MainPage;
import com.google.common.collect.Lists;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieTVShowDisplayPage extends AppCompatActivity {

    private TextView movieName, movieOverview;
    private ImageView moviePoster, movieBackdrop;
    public Button addItemToListButton;
    ArrayList<String> ListNameArray = new ArrayList<String>();
    ArrayList<Integer> ListIDArray = new ArrayList<Integer>();
    String passedMediaID;
    int passedUserID;
    String passedMediaType;
    String posterURL;
    String backdropURL;
    String movieID;
    String mediaName;
    String passedMediaName;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private JSONObject returnObject;
    public ArrayList<JSONObject> ListObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_tvshow_display_page);

        Intent intent = getIntent();
        passedMediaID = intent.getStringExtra("id");
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedMediaType = intent.getStringExtra("mediatype");
        passedMediaName = intent.getStringExtra("medianame");

        Toast.makeText(this, "User ID: " + passedUserID, Toast.LENGTH_SHORT).show();

        if (passedMediaType.equals("movie"))
        {
            GetDataMovie(passedMediaID);
        }
        else if (passedMediaType.equals("tv"))
        {
            GetDataTV(passedMediaID);
        }



        //GetAllLists(passedUserID);

        addItemToListButton = findViewById(R.id.addItemButton);
        movieName = findViewById(R.id.movieNameTextView);
        moviePoster = findViewById(R.id.moviePosterImageView);
        movieOverview = findViewById(R.id.movieOverViewTextView);
        movieBackdrop = findViewById(R.id.movieBackdropImageView);

        addItemToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), AddItemToListPage.class);

                int mediaIDConvertToInt = Integer.parseInt(passedMediaID);

                intent.putExtra("mediaid", (mediaIDConvertToInt));
                intent.putExtra("mediatype", passedMediaType);
                intent.putExtra("posterurl", posterURL);
                intent.putExtra("medianame", mediaName);

                view.getContext().startActivity(intent);

            }
        });

    }

    private void GetDataMovie(String id) {

        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US";

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    // now we get our response from API in json object format.
                    // in below line we are extracting a string with its key
                    // value from our json object.
                    // similarly we are extracting all the strings from our json object.
                    String name = response.getString("original_title");
                    String overview = response.getString("overview");
                    String ID = response.getString("id");
                    String URL = response.getString("poster_path");
                    String backdrop = response.getString("backdrop_path");

                    // after extracting all the data we are
                    // setting that data to all our views.
                    movieName.setText(name);
                    movieOverview.setText(overview);
                    movieID = ID;
                    mediaName = name;
                    posterURL = URL;
                    backdropURL = backdrop;

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + posterURL)
                            .into(moviePoster);

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + backdropURL)
                            .into(movieBackdrop);


                } catch (JSONException e) {
                    // if we do not extract data from json object properly.
                    // below line of code is use to handle json exception
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // this is the error listener method which
            // we will call if we get any error from API.
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(MovieTVShowDisplayPage.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        mRequestQueue.add(jsonObjectRequest);
    }

    private void GetDataTV(String id) {

        String url = "https://api.themoviedb.org/3/tv/" + id + "?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US";



        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    // now we get our response from API in json object format.
                    // in below line we are extracting a string with its key
                    // value from our json object.
                    // similarly we are extracting all the strings from our json object.
                    String name = response.getString("original_name");
                    String overview = response.getString("overview");
                    String ID = response.getString("id");
                    String URL = response.getString("poster_path");
                    String backdrop = response.getString("backdrop_path");

                    // after extracting all the data we are
                    // setting that data to all our views.
                    movieName.setText(name);
                    movieOverview.setText(overview);
                    movieID = ID;
                    posterURL = URL;
                    backdropURL = backdrop;
                    mediaName = name;

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + posterURL)
                            .into(moviePoster);

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + backdropURL)
                            .into(movieBackdrop);


                } catch (JSONException e) {
                    // if we do not extract data from json object properly.
                    // below line of code is use to handle json exception
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // this is the error listener method which
            // we will call if we get any error from API.
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(MovieTVShowDisplayPage.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        mRequestQueue.add(jsonObjectRequest);
    }

    public void GetAllLists(int userID){

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
                            int listID = list.getInt("id");

                            ListNameArray.add(listName);
                            ListIDArray.add(listID);

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