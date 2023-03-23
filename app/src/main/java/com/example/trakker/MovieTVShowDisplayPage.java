package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieTVShowDisplayPage extends AppCompatActivity {

    private TextView movieName, movieOverview;
    private ImageView moviePoster, movieBackdrop;

    String movieID;

    String posterURL;

    String backdropURL;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_tvshow_display_page);

        Intent intent = getIntent();
        String ID = intent.getStringExtra("id");

        getData(ID);

        movieName = findViewById(R.id.movieNameTextView);
        moviePoster = findViewById(R.id.moviePosterImageView);
        movieOverview = findViewById(R.id.movieOverViewTextView);
        movieBackdrop = findViewById(R.id.movieBackdropImageView);

    }

    private void getData(String id) {

        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US";

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                /*
                // inside the on response method.
                // we are hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // in below line we are making our card
                // view visible after we get all the data.
                courseCV.setVisibility(View.VISIBLE);
                */
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
                    posterURL = URL;
                    backdropURL = backdrop;
/*

                    // we are using picasso to load the image from url.
                    Picasso.get().load("https://image.tmdb.org/t/p/w500/" + posterURL).into(moviePoster);

                    Picasso.get().load("https://image.tmdb.org/t/p/w500/" + backdropURL).into(movieBackdrop);
*/

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

}