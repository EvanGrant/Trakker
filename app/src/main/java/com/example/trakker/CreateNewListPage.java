package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateNewListPage extends AppCompatActivity {

    private Button submitNewListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list_page);

        submitNewListButton = findViewById(R.id.SubmitNewListButton);

        submitNewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ListRequest.CreateNewList

                //Intent intent = new Intent(view.getContext(), Registration.class);
                //view.getContext().startActivity(intent);

            }
        });


    }
}