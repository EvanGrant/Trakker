package com.example.trakker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    private EditText EditTextEmail;
    private EditText EditTextPassword;
    private EditText EditTextConfirmPassword;
    private EditText EditTextFirstName;
    private EditText EditTextLastName;
    private Button RegisterButton;



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







    }
}