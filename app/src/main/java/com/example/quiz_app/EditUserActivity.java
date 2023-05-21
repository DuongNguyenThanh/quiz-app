package com.example.quiz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.User;

import java.util.regex.Pattern;

public class EditUserActivity extends AppCompatActivity {

    private Button btnSaveUser, btnCancel, changeAvt;
    private EditText txtUsername, txtDob;
    private User user;
    private UserDAO mUserDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        initView();
        mUserDAO = new UserDAO(this);
        Intent intent = new Intent();

        Integer uId = getIntent().getIntExtra("user-id", 0);
        user = (User) getIntent().getSerializableExtra("user");
        user.setId(uId);

        //Set back data for txtUsername and txtDob
        txtUsername.setText(user.getName());
        txtDob.setText(user.getDob());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(mUserDAO);
            }
        });
    }

    private void saveUser(UserDAO userDAO) {

        String DATE_PATTERN = "^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$";

        String uName = txtUsername.getText().toString();
        String dob = txtDob.getText().toString();

        if(TextUtils.isEmpty(uName)) {
            Toast.makeText(EditUserActivity.this, "Name is required!", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(dob)) {
            Toast.makeText(EditUserActivity.this, "Date of birth is required!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Pattern.matches(DATE_PATTERN, dob)) {
            Toast.makeText(EditUserActivity.this, "Your date of birth must be day/month/year!", Toast.LENGTH_LONG).show();
            return;
        }

        user.setName(uName);
        user.setDob(dob);

        boolean updateSuccessful = userDAO.updateUser(user);
        if(updateSuccessful) {
            final Intent data = new Intent();

            // pass data into intent
            data.putExtra("newName", uName);
            data.putExtra("newDob", dob);

            // set resultCode is Activity.RESULT_OK
            setResult(Activity.RESULT_OK, data);
        } else {
            Toast.makeText(EditUserActivity.this, "Update user profile fail", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void initView() {

        btnSaveUser = findViewById(R.id.saveUser);
        btnCancel = findViewById(R.id.cancel);
        changeAvt = findViewById(R.id.changeAvt);
        txtUsername = findViewById(R.id.fullName);
        txtDob = findViewById(R.id.dob);
    }
}
