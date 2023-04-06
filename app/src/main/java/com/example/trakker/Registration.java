package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.trakker.MainPagePackage.MainPage;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText EditTextEmail;
    private EditText EditTextPassword;
    private EditText EditTextConfirmPassword;
    private EditText EditTextFirstName;
    private EditText EditTextLastName;
    private Button RegisterButton;

    public String fName;
    public String lName;
    public String email;
    public String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        EditTextFirstName = findViewById(R.id.FirstNameRegistration);
        EditTextLastName = findViewById(R.id.LastNameRegistration);
        EditTextEmail = findViewById(R.id.EmailRegistration);
        EditTextPassword = findViewById(R.id.PasswordRegistration);
        EditTextConfirmPassword = findViewById(R.id.ConfirmPasswordRegistration);
        RegisterButton = findViewById(R.id.ConfirmRegistrationButton);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fName = EditTextFirstName.getText().toString();
                lName = EditTextLastName.getText().toString();
                email = EditTextEmail.getText().toString();
                password = EditTextPassword.getText().toString();

                RegisterUser(view);

            }
        });

    }

    public void RegisterUser(View v){

        RequestQueue queue = Volley.newRequestQueue(Registration.this);
        String url = "http://10.0.2.2:4000/users/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Registration.this, "Added User to List!", Toast.LENGTH_SHORT).show();


                        try {

                            JSONObject returnObject = new JSONObject(response);

                            int userID = Integer.parseInt(returnObject.getString("id"));

                            if(userID > 0){

                                Intent intent = new Intent(v.getContext(), MainPage.class);
                                v.getContext().startActivity(intent);
                                intent.putExtra("userID", userID);
                                intent.putExtra("firstname", fName);

                            }else{

                                Toast.makeText(Registration.this, "Incorrect Login", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(Registration.this, "user is already in database", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){

                Map<String, String> paramV = new HashMap<>();
                paramV.put("FirstName", fName);
                paramV.put("LastName", lName);
                paramV.put("Email", email);
                paramV.put("Password", password);

                return paramV;

            }
        };
        queue.add(stringRequest);


    }
}