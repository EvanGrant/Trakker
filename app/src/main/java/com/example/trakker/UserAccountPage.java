package com.example.trakker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.trakker.MainPagePackage.MainPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserAccountPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_page);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.UserAccount);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.MainPage:
                        startActivity(new Intent(getApplicationContext(), MainPage.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.UserAccount:
                        return true;

                }
                return false;
            }
        });
    }
}
