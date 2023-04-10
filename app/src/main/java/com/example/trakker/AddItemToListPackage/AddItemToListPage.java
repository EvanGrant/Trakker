package com.example.trakker.AddItemToListPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.CreateNewListPage;
import com.example.trakker.GlobalClass;
import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.R;
import com.example.trakker.SearchPagePackage.SearchPage;
import com.example.trakker.ShowListsPackage.ListItems;
import com.example.trakker.ShowListsPackage.ShowListsAdapter;
import com.example.trakker.ShowListsPackage.ShowListsPage;
import com.example.trakker.UserAccountPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class AddItemToListPage extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    RecyclerView recyclerView;
    AddItemToListAdapter adapter;

    int passedMediaID;
    String passedMediaType;
    String passMediaName;
    String passedMediaPosterURL;

    GlobalClass g = new GlobalClass();

    Context context = this;

    List<ListItems> listNames = new ArrayList<ListItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_list_page);

        Intent intent = getIntent();
        passedMediaID = intent.getIntExtra("mediaid", 0);
        passedMediaType = intent.getStringExtra("mediatype");
        passedMediaPosterURL = intent.getStringExtra("posterurl");
        passMediaName = intent.getStringExtra("medianame");


        try {
            run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        recyclerView = findViewById(R.id.rvAddItemToLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddItemToListAdapter(getApplicationContext(), listNames, passedMediaID, passedMediaType, passMediaName, passedMediaPosterURL);
        recyclerView.setAdapter(adapter);

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

    public void run() throws Exception{

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://10.0.2.2:4000/lists/" + g.getUserID())
                .build();

        Callback callback = new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String myResponse = response.body().string();

                try {

                    JSONArray jsonArray = new JSONArray(myResponse);


                    for (int i = 0; i < jsonArray.length(); i++)
                    {

                        JSONObject list = jsonArray.getJSONObject(i);

                        int listID = list.getInt("id");
                        String listName = list.getString("ListName");

                        //Info is getting in correctly, but because its async its not being filled into the recyclerview correctly
                        listNames.add(new ListItems(listName, listID));

                    }



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                AddItemToListPage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, myResponse, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };

        client.newCall(request).enqueue(callback);

    }

}

