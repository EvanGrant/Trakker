package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.APIRequests.ListRequest;
import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.ShowListContentsPackage.ShowListContentsPage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LogIn extends AppCompatActivity {



    Button signinbutton;
    Button createacctbutton;

    private JSONArray returnObject;
    public String listName;
    public ArrayList<JSONObject> ListObject;

/*
    ImageView testImageView;
    //test image retrieval with volley and api
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        signinbutton = findViewById(R.id.signInBtn);

        createacctbutton = findViewById(R.id.CreateacctBtn);

        /*
        getData();
        testImageView = findViewById(R.id.testImageView);
        Picasso.get().load(url).into(testImageView);
        */

        GetAllLists(1);

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainPage.class);
                view.getContext().startActivity(intent);}
        });

        createacctbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowListContentsPage.class);
                view.getContext().startActivity(intent);}
        });

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

                    for (int i = 0; i < response.length(); i++) {

                        returnObject = new JSONArray(response);

                        JSONObject jsonobject = returnObject.getJSONObject(i);

                        String listID = jsonobject.getString("id");
                        String userID = jsonobject.getString("UserId");
                        String listName = jsonobject.getString("ListName");

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




/*

    private void getData() {
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Response", "Error : Volley Request did not work" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
*/


}