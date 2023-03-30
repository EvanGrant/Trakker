package com.example.trakker;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListRequest {
    /*
     *  ListRequest.GetAllLists
     *  parameter: userID
     * //Return all lists of user with userID
     *
     *  ListRequest.AddList
     *  parameter: userID
     *  //Creates new list acquainted with userID
     *
     * */

    private MainPage context;
    private JSONObject returnObject;

    public JSONObject GetAllLists(int userID){

        RequestQueue mRequestQueue;
        StringRequest mStringRequest;

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context);

        String RESTUrl = "localhost:4000/lists/" + Integer.toString(userID);

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, RESTUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {

                    returnObject = new JSONObject(response);

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

        return returnObject;

    }


}
