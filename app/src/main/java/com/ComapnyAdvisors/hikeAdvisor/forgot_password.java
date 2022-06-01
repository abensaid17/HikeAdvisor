package com.ComapnyAdvisors.hikeAdvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ComapnyAdvisors.hikeAdvisor.Util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    private static final String TAG = "forgot_password";
    private Button emailRequest;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailRequest = findViewById(R.id.requestButton);
        mEmail = findViewById(R.id.email);

        emailRequest.setOnClickListener(view -> {
            sendPasswordReset(mEmail.getText().toString().trim());
        });
    }


    public void sendPasswordReset(String email) {
        // [START send_password_reset]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        boolean everythingOk = true;
        String emailAddress = email.trim();

        if (email.isEmpty()) {
            mEmail.setError("Email can't be Empty!");
            mEmail.requestFocus();
            everythingOk = false;
        }

        if(!Utils.isValidEmail(email)){
            mEmail.setError("Invalid email!");
            mEmail.requestFocus();
            everythingOk = false;
        }

        if (everythingOk) {
            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                                Toast.makeText(forgot_password.this, "Check your Email!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(forgot_password.this, logIn.class));
                            } else {
                                Toast.makeText(forgot_password.this, "Unable to send email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}