package com.example.trakker.ShowListContentsPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class ShowListContentsPage extends AppCompatActivity {

    MyAdapter adapter;
    RecyclerView recyclerView;

    Context context = this;

    int passedListID;

    GlobalClass g = new GlobalClass();

    List<Item> items = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_contents_page);

        Intent intent1 = getIntent();
        passedListID = intent1.getIntExtra("listid", 0);

        //Toast.makeText(this, "list id" + passedListID, Toast.LENGTH_SHORT).show();



        try {
            run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        recyclerView = findViewById(R.id.rvShowListContents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        //items.add(new Item("John wick","john.wick@email.com", "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg"));

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
                .url("http://10.0.2.2:4000/moviesInList/" + passedListID)
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

                        int listID = list.getInt("ListId");
                        String movieID = list.getString("MovieId");
                        String mediaType = list.getString("MediaType");
                        String posterURL = list.getString("posterURL");
                        String mediaName = list.getString("Title");

                        //items.add(new Item("Keanu Reeves","Keanu.Reeves@email.com", "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg"));
                        items.add(new Item(mediaName, movieID, "https://image.tmdb.org/t/p/w500/" + posterURL));

                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                ShowListContentsPage.this.runOnUiThread(new Runnable() {
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