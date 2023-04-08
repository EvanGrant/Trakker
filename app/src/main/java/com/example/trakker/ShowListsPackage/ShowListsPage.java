package com.example.trakker.ShowListsPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.GlobalClass;
import com.example.trakker.R;
import com.example.trakker.ShowListContentsPackage.Item;
import com.example.trakker.ShowListContentsPackage.MyAdapter;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowListsPage extends AppCompatActivity {

    GlobalClass g = new GlobalClass();

    List<ListItems> listNames = new ArrayList<ListItems>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lists_page);

        RecyclerView recyclerView = findViewById(R.id.rvShowLists);

        int userID = (g.getUserID());

        GetListNames(userID);

        //listNames.add(new ListItems("1st list", 2));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ShowListsAdapter(getApplicationContext(),listNames));


    }



    public void GetListNames(int userID){

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


    }


    interface OnTasksRetrieved {
        void getResult(List<Task> result);
    }

}