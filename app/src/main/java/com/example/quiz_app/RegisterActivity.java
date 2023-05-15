package com.example.quiz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister, btnCancel;
    private EditText txtUsername, txtPassword;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void register() {

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(RegisterActivity.this, "Username is required", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "Password is required", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(RegisterActivity.this,
                                "Register Success", Toast.LENGTH_SHORT).show();

                        final Intent data = new Intent();

                        // pass data into intent
                        data.putExtra("email", username);
                        data.putExtra("pass", password);

                        // set resultCode is Activity.RESULT_OK
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                    }
                });
        }

    private void initView() {
        btnRegister = findViewById(R.id.registerButton);
        btnCancel = findViewById(R.id.cancelButton);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
    }
}
