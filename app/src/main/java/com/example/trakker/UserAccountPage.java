package com.example.trakker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.SearchPagePackage.SearchPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class UserAccountPage extends AppCompatActivity {

    public GlobalClass globalClass = new GlobalClass();

    public TextView fullNameTextView;
    public TextView emailTextView;

    private String firstName;
    private String lastName;
    private String email;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_page);

        firstName = globalClass.getUserFirstName();
        lastName = globalClass.getUserLastName();
        email = globalClass.getUserEmail();

        fullNameTextView = findViewById(R.id.AccountNameTextBox);
        emailTextView = findViewById(R.id.AccountEmailTextBox);

        fullNameTextView.setText(firstName + " " + lastName);
        emailTextView.setText(email);

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

                    case R.id.Search:
                        startActivity(new Intent(getApplicationContext(), SearchPage.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}
