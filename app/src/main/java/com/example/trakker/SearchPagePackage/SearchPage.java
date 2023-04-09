package com.example.trakker.SearchPagePackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.R;
import com.example.trakker.UserAccountPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {

    // creating variables for
    // our ui components.
    private RecyclerView courseRV;
    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModel> courseModelArrayList;
    ArrayList<String> movieIDArray = new ArrayList<>();
    ArrayList<String> movieTitleArray = new ArrayList<>();
    ArrayList<String> moviePosterArray = new ArrayList<>();
    ArrayList<String> movieBackDropArray = new ArrayList<>();
    ArrayList<String> mediaTypeArray = new ArrayList<>();

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private String url = "https://api.themoviedb.org/3/search/multi?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&query=Batman&page=1&include_adult=false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);

        // calling method to
        // build recycler view.
        buildRecyclerView();

    }


    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


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
                        startActivity(new Intent(getApplicationContext(), UserAccountPage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.MainPage:
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        overridePendingTransition(0,0);

                    case R.id.Search:
                        return true;


                }

                return false;

            }
        });



        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.e("Query Text", "Query" + query);

                //String convertedQueryForAPISearch = query.toString();

                getData(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                //filter(newText);
                return false;
            }
        });
        return true;
    }



    private void buildRecyclerView() {

        // below line we are creating a new array list
        courseModelArrayList = new ArrayList<CourseModel>();

        // below line is to add data to our array list.
        /*
        courseModelArrayList.add(new CourseModel("DSA", "DSA Self Paced Course"));
        */
        //getData();

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModelArrayList, SearchPage.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }

    private void getData(String query) {

        courseModelArrayList.clear();

        movieTitleArray.clear();
        movieBackDropArray.clear();
        moviePosterArray.clear();
        movieIDArray.clear();

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/search/multi?api_key=a5c71b671673e424ff2b1612c09940d1&language=en-US&query=" + query + "&include_adult=false", new Response.Listener<String>()
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

                            //filters the jsonobject to not include people in the API pull request, only puts in TV and movie in array
                            String mediatype = movie.get("media_type").toString();

                            if (mediatype.equals("movie"))
                            {
                                String movieID = movie.getString("id");
                                String movieTitle = movie.getString("title");
                                String moviePoster = movie.getString("poster_path");
                                String backDropPoster = movie.getString("backdrop_path");

                                movieIDArray.add(movieID);
                                movieTitleArray.add(movieTitle);
                                moviePosterArray.add(moviePoster);
                                movieBackDropArray.add(backDropPoster);
                                mediaTypeArray.add(mediatype);


                                courseModelArrayList.add(new CourseModel(movieTitle, movieID, moviePoster, backDropPoster, movieID, mediatype));
                            }
                            else if (mediatype.equals("tv"))
                            {
                                String movieID = movie.getString("id");
                                String movieTitle = movie.getString("name");
                                String moviePoster = movie.getString("poster_path");
                                String backDropPoster = movie.getString("backdrop_path");

                                movieIDArray.add(movieID);
                                movieTitleArray.add(movieTitle);
                                moviePosterArray.add(moviePoster);
                                movieBackDropArray.add(backDropPoster);
                                mediaTypeArray.add(mediatype);

                                courseModelArrayList.add(new CourseModel(movieTitle, movieID, moviePoster, backDropPoster, movieID, mediatype));

                            }
                            else if (mediatype.equals("person"))
                            {
                                continue;
                            }
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




    /*private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<CourseModel> filteredlist = new ArrayList<CourseModel>();

        // running a for loop to compare elements.
        for (CourseModel item : courseModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            //Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }*/

}
