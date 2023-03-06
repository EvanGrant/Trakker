package com.example.trakker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);





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
                        startActivity(new Intent(getApplicationContext(),UserAccountPage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.MainPage:

                        startActivity(new Intent(getApplicationContext(),MainPage.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.Search:
                        return true;


                }

                return false;

            }
        });


    }
}