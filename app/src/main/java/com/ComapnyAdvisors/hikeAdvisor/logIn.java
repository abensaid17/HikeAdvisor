package com.ComapnyAdvisors.hikeAdvisor;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;


public class logIn extends AppCompatActivity {
    private static final String TAG = "logIn";
    private static final int RC_SIGN_IN = 5;
    private Button mRegisterButton;
    private Button mLogInButton;
    private Button forgotPassword;
    private SignInButton mSignIn;
    private EditText mEmail;
    private EditText mPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    GoogleSignInClient mGoogleSignInClient;


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
        mSignIn = (SignInButton) findViewById(R.id.sign_in_button);
        forgotPassword = (Button) findViewById(R.id.forgot_password);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        //Google Sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        mLogInButton.setOnClickListener(view -> {
            signInEmailPassword(mEmail.getText().toString().trim(), mPassword.getText().toString().trim());
        });

        mRegisterButton.setOnClickListener(view -> {
            startActivity(new Intent(logIn.this, Register.class));
        });

        mSignIn.setOnClickListener(view -> {
            signIn();
        });

        forgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(logIn.this,forgot_password.class));
        });
    }

//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            startActivity(new Intent(logIn.this, MapsActivity.class));
//            finish();
//        }
//    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activityResultLauncher.launch(signInIntent);
    }


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        try {
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            firebaseAuthWithGoogle(account);
                        } catch (ApiException e) {
                            Toast.makeText(logIn.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(logIn.this,MapsActivity.class));
                }else{
                    Toast.makeText(logIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void signInEmailPassword(String email, String password) {
        Boolean everythingOk = true;

        if (email.isEmpty()) {
            mEmail.setError("Email can't be empty!");
            mEmail.requestFocus();
            everythingOk = false;
        }

        if (password.isEmpty()) {
            mPassword.setError("Password can't be empty!");
            mPassword.requestFocus();
            everythingOk = false;
        }

        if (everythingOk) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                updateUI();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(logIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private void updateUI() {
        startActivity(new Intent(logIn.this,MapsActivity.class));
        finish();
    }
}