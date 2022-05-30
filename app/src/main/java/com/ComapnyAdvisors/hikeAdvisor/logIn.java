package com.ComapnyAdvisors.hikeAdvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.ComapnyAdvisors.hikeAdvisor.Util.Utils;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class logIn extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyprefsFile";
    private Button mRegisterButton;
    private Button mLogInButton;
    private EditText mEmail;
    private EditText mPassword;
    private FirebaseAuth mAuth;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //EditText
        mEmail = findViewById(R.id.editTextTextEmailAddress);
        mPassword = findViewById(R.id.editTextTextPassword);

        //Button
        mLogInButton = (Button) findViewById(R.id.LogInButton);
        mRegisterButton = (Button) findViewById(R.id.signUpButton);

//        mRegisterButton.setOnClickListener(this);
//        mLogInButton.setOnClickListener(this);


//        //Firebase
//        mAuth = FirebaseAuth.getInstance();
//
//        //Google Sign in
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();


        mLogInButton.setOnClickListener(view -> {
            startActivity(new Intent(logIn.this,MapsActivity.class));
        });

        mRegisterButton.setOnClickListener(view ->{
            startActivity(new Intent(logIn.this,Register.class));
        });
    }

//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
////            startActivity(new Intent(logIn.this,profile.class));
////            finish();
//        }
//    }
//
//    public void passwordEmailCheck(){
//        String email = mEmail.getText().toString().trim();
//        String password = mPassword.getText().toString().trim();
//
//        if(email.isEmpty()){
//            mEmail.setError("Email required!");
//            mEmail.requestFocus();
//        }
//
//        if(password.isEmpty()){
//            mPassword.setError("Password required!");
//            mPassword.requestFocus();
//        }
//
//        if(!Utils.isValidEmail(email)){
//            mEmail.setError("Invalid Email");
//        }
//
//        if(!Utils.isValidPassword(password)){
//            mPassword.setError("Invalid password, should have uppercase and one special character.");
//        }
//    }


}