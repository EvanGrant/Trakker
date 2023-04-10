package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.ShowListsPackage.ShowListsPage;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateNewListPage extends AppCompatActivity {

    GlobalClass g = new GlobalClass();

    private Button submitNewListButton;

    private EditText ListNameEditText;

    int userID;
    String passedListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list_page);

        userID = g.getUserID();

        submitNewListButton = findViewById(R.id.SubmitNewListButton);

        ListNameEditText = findViewById(R.id.CreateListEditText);

        submitNewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                passedListName = ListNameEditText.getText().toString();

                RegisterList(passedListName, view);

                Intent intent = new Intent(view.getContext(), Registration.class);
                view.getContext().startActivity(intent);

            }
        });
    }


    public void RegisterList(String passedListName, View v){

        RequestQueue queue = Volley.newRequestQueue(CreateNewListPage.this);
        String url = "http://10.0.2.2:4000/list/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateNewListPage.this, "Added List!", Toast.LENGTH_SHORT).show();

                        try {

                            JSONObject returnObject = new JSONObject(response);

                            int userID = Integer.parseInt(returnObject.getString("id"));

                            if(userID > 0){

                                Intent intent = new Intent(v.getContext(), ShowListsPage.class);

                                v.getContext().startActivity(intent);

                            }else{

                                Toast.makeText(CreateNewListPage.this, "Invalid List", Toast.LENGTH_SHORT).show();

                            }


                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        //go to the next page and add userId as a variable

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateNewListPage.this, "list is already in database", Toast.LENGTH_SHORT).show();
            }
        }){

            protected Map<String, String> getParams(){

                Map<String, String> paramV = new HashMap<>();
                paramV.put("UserId", String.valueOf(userID));
                paramV.put("ListName", passedListName);

                return paramV;

            }
        };

        queue.add(stringRequest);

    }

}