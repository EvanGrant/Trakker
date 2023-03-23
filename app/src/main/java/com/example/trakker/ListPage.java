package com.example.trakker;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ListPage extends AppCompatActivity {


    String UserList[]= {"Favorite Movie", "Favorite Show", "Action Movie", "Comedy Movies"};

        ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);
        listView= (ListView) findViewById(R.id.Listpage);
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),UserList);

       listView.setAdapter(listAdapter);

    }
}
