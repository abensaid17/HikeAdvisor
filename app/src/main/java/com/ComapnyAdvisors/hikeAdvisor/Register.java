package com.ComapnyAdvisors.hikeAdvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ComapnyAdvisors.hikeAdvisor.Util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class Register extends AppCompatActivity {

    private EditText inputName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputRepeatPassword;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.registerName);
        inputEmail = findViewById(R.id.registerEmail);
        inputPassword = findViewById(R.id.registerPassword);
        inputRepeatPassword = findViewById(R.id.registerRepeatedPassword);
        Button mSignUpButton = (Button) findViewById(R.id.sign_up);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mSignUpButton.setOnClickListener(view -> {
            register();
        });

    }


    public void register() {
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String repeatedPassword = inputRepeatPassword.getText().toString().trim();

        Boolean everythingOk = true;

        if (name.isEmpty()) {
            inputName.setError("Full name required!");
            inputName.requestFocus();
            everythingOk = false;
        }

        if (email.isEmpty()) {
            inputEmail.setError("Email is required!");
            inputEmail.requestFocus();
            everythingOk = false;
        }

        if (password.isEmpty()) {
            inputPassword.setError("Password is required!");
            inputPassword.requestFocus();
            everythingOk = false;
        }

        if (repeatedPassword.isEmpty()) {
            inputRepeatPassword.setError("Repeated-password is required!");
            inputRepeatPassword.requestFocus();
            everythingOk = false;
        }

        if (!Utils.isValidEmail(email)) {
            inputEmail.setError("Please provide valid Email!");
            inputEmail.requestFocus();
            everythingOk = false;
        }

        if (!Utils.isValidPassword(password)) {
            inputPassword.setError("Please provide valid Password!");
            inputPassword.requestFocus();
            everythingOk = false;
        }

        if (!repeatedPassword.equals(password)) {
            inputRepeatPassword.setError("Repeated password should be equal to password");
            inputRepeatPassword.requestFocus();
            everythingOk = false;
        }


        if (everythingOk) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        sendUserToLogIn();
                        Toast.makeText(Register.this, "Registration Succesful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Register.this, "Not possible to Register"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToLogIn() {
        startActivity(new Intent(Register.this,logIn.class));
    }

}