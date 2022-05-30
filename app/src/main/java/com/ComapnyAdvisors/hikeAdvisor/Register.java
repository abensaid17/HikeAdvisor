package com.ComapnyAdvisors.hikeAdvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ComapnyAdvisors.hikeAdvisor.Util.Utils;

public class Register extends AppCompatActivity{

    private EditText inputName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText  inputRepeatPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.registerName);
        inputEmail = findViewById(R.id.registerEmail);
        inputPassword = findViewById(R.id.registerPassword);
        inputRepeatPassword = findViewById(R.id.registerRepeatedPassword);
        Button mSignUpButton = (Button) findViewById(R.id.sign_up);


        mSignUpButton.setOnClickListener(view -> {
            signIn();
        });

    }


    public void signIn(){
       String name = inputName.getText().toString().trim();
       String email = inputEmail.getText().toString().trim();
       String password = inputPassword.getText().toString().trim();
       String repeatedPassword = inputRepeatPassword.getText().toString().trim();

        if(name.isEmpty()){
            inputName.setError("Full name required!");
            inputName.requestFocus();
        }

        if(email.isEmpty()){
            inputEmail.setError("Email is required!");
            inputEmail.requestFocus();
        }

        if(password.isEmpty()){
            inputPassword.setError("Password required!");
            inputPassword.requestFocus();
        }

        if(repeatedPassword.isEmpty()){
            inputRepeatPassword.setError("Repeated-password required!");
            inputRepeatPassword.requestFocus();
        }

        if(!Utils.isValidEmail(email)){
            inputEmail.setError("Please provide valid Email!");
            inputEmail.requestFocus();
        }
    }


}