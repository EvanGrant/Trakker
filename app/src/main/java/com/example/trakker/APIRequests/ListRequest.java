package com.example.trakker.APIRequests;

import android.content.Context;
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

    //private MainPage context;
    //private JSONArray returnObject;

    //public Integer userID;
    //public String listName;
   // public Integer listID;

/*

    public JSONArray GetAllLists(int userID, Context mContext){

        RequestQueue mRequestQueue;
        StringRequest mStringRequest;

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(mContext);

        //String RESTUrl = "localhost:4000/lists/" + userID;

        String RESTUrl = "http://10.0.2.2:4000/lists/" + userID;

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, RESTUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                listName = response.toString();

              */
/*
                try

                {

                    returnObject = new JSONArray(response);



                    *//*
*/
/*JSONArray jsonarray = new JSONArray(returnObject);

                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                        String listID = jsonobject.getString("id");
                        String userID = jsonobject.getString("UserId");
                        String listName = jsonobject.getString("ListName");

                    }*//*
*/
/*

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                *//*


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("Volley Response", "Error : Volley Request did not work" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

        return returnObject;


    }

*/


}
