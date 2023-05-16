package com.example.quiz_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_REGISTER = 10000;

    private Button btnLogin;
    private EditText txtUsername, txtPassword;
    private TextView tvResetPass, tvRegister;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        tvResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword(view);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_REGISTER) {
            if(resultCode == Activity.RESULT_OK) {
                // take data from Intent
                final String username = data.getStringExtra("username");
                final String password = data.getStringExtra("password");

                //Set back data for txtEmail and password
                txtUsername.setText(username);
                txtPassword.setText(password);
            } else {
                // DetailActivity fail
            }
        }
    }

    private void resetPassword(View v) {

        EditText resetMail = new EditText(v.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
        passwordResetDialog.setTitle("Reset Password?");
        passwordResetDialog.setMessage("Enter your email to received reset link");
        passwordResetDialog.setView(resetMail);

        passwordResetDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = resetMail.getText().toString();
                mAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this, "Reset link sent to your email", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error! Reset link not sent", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        passwordResetDialog.create().show();
    }

    private void register() {
        Intent intent = new Intent(LoginActivity.this,
                RegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }

    private void login() {

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(LoginActivity.this, "Username is required", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Password is required", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(mAuth.getCurrentUser().isEmailVerified()) {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Please verify your email address", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this,
                                "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {

        btnLogin = findViewById(R.id.loginButton);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        tvResetPass = findViewById(R.id.forgotPassword);
        tvRegister = findViewById(R.id.gotoRegister);
    }
}
