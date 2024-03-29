package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.trakker.APIRequests.ListRequest;
import com.example.trakker.MainPagePackage.MainPage;
import com.example.trakker.ShowListContentsPackage.ShowListContentsPage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    Button signinbutton;
    Button createacctbutton;

    private JSONObject returnObject;
    public String listName;
    public ArrayList<JSONObject> ListObject;
    public String email;
    public String password;

    public String FirstName;
    public String LastName;
    public int userID;
    public

    EditText editTextEmail;
    EditText editTextPassword;

    public GlobalClass globalClass = new GlobalClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        signinbutton = findViewById(R.id.signInBtn);
        createacctbutton = findViewById(R.id.CreateacctBtn);

        editTextEmail = findViewById(R.id.usernamelogin);
        editTextPassword = findViewById(R.id.passwordlogin);



        /*
        getData();
        testImageView = findViewById(R.id.testImageView);
        Picasso.get().load(url).into(testImageView);
        */

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();

                GetUserInfo(email, password, view); //use the variables FirstName, LastName, and userID

            }
        });

        createacctbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Registration.class);
                view.getContext().startActivity(intent);}
        });

    }

    public void GetUserInfo(String username, String password, View view){

        RequestQueue mRequestQueue;
        StringRequest mStringRequest;

        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        String RESTUrl = "http://10.0.2.2:4000/users/" + username + "/" + password;

        // String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, RESTUrl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try {

                    returnObject = new JSONObject(response);

                    userID = Integer.parseInt(returnObject.getString("id"));
                    LastName = returnObject.getString("LastName");
                    FirstName = returnObject.getString("FirstName");
                    email = returnObject.getString("Email");

                    if(userID > 0){

                        globalClass.setUserID(userID);
                        globalClass.setUserEmail(email);
                        globalClass.setUserFirstName(FirstName);
                        globalClass.setUserLastName(LastName);

                        Intent intent = new Intent(view.getContext(), MainPage.class);

                        intent.putExtra("firstname", FirstName);
                        intent.putExtra("userID", userID);

                        view.getContext().startActivity(intent);




                    }else{

                        Toast.makeText(LogIn.this, "Incorrect Login", Toast.LENGTH_SHORT).show();

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


}