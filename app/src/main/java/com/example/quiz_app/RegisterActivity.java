package com.example.quiz_app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText txtEmail, txtUsername, txtPassword, txtConfirmPass, txtDob;
    private TextView tvLogin;
    private UserDAO mUserDAO;
    protected FirebaseAuth mAuth;
    protected ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Creating new account...");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(dialog);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get((Calendar.MONTH));
                int dom = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yyyy, int MM, int dd) {
                        txtDob.setText(dd + "/" + (MM+1) + "/" + yyyy);
                    }
                }, year, month, dom);
                dialog.show();
            }
        });
    }

    private void register(ProgressDialog progressDialog) {

        String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        String email = txtEmail.getText().toString();
        String username = txtUsername.getText().toString();
        String sDob = txtDob.getText().toString();
        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPass.getText().toString();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(RegisterActivity.this, "Username is required!", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "Password is required!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Pattern.matches(EMAIL_PATTERN, email)) {
            Toast.makeText(RegisterActivity.this, "Please fill in your right email!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!confirmPassword.equals(password)) {
            Toast.makeText(RegisterActivity.this, "Confirm password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()) {

                                    String uId = authResult.getUser().getUid();
                                    Date dateDob = new Date();
                                    try {
                                        dateDob = new SimpleDateFormat("dd/MM/yyyy").parse(sDob);
                                    }catch(Exception ex) {

                                    }
                                    User user = new User(username, dateDob, uId);
                                    mUserDAO.addUser(user);

                                    Toast.makeText(RegisterActivity.this,
                                            "Registered successfully. Please check your gmail for verification.", Toast.LENGTH_SHORT).show();

                                    final Intent data = new Intent();

                                    // pass data into intent
                                    data.putExtra("email", email);
                                    data.putExtra("pass", password);

                                    // set resultCode is Activity.RESULT_OK
                                    setResult(Activity.RESULT_OK, data);
                                    finish();
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Email exist", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void initView() {
        btnRegister = findViewById(R.id.registerButton);
        tvLogin = findViewById(R.id.gotoLogin);
        txtEmail = findViewById(R.id.username);
        txtUsername = findViewById(R.id.fullName);
        txtPassword = findViewById(R.id.password);
        txtConfirmPass = findViewById(R.id.confirmPassword);
        txtDob = findViewById(R.id.dob);
    }
}
