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

import com.bumptech.glide.Glide;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.Image;
import com.example.quiz_app.model.User;
import com.example.quiz_app.model.enumtype.ImageTypeEnum;

import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_IMAGE = 10001;

    private CircleImageView avtUser;
    private Button btnSaveUser, btnCancel, chooseImage;
    private EditText txtUsername, txtDob;
    private User user;
    private UserDAO mUserDAO;
    private Image image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        initView();
        image = new Image();
        mUserDAO = new UserDAO(this);

        Integer uId = getIntent().getIntExtra("user-id", 0);
        user = (User) getIntent().getSerializableExtra("user");
        String srcImg = getIntent().getStringExtra("avt-src");
        user.setId(uId);

        //Set back data for txtUsername and txtDob
        txtUsername.setText(user.getName());
        txtDob.setText(user.getDob());
        Glide.with(this)
                .load(srcImg)
                .into(avtUser);

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

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUserActivity.this, ImageActivity.class);
                intent.putExtra("image-type", ImageTypeEnum.AVATAR.name());
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE) {
            if(resultCode == Activity.RESULT_OK) {
                // take data from Intent
                final String srcImage = data.getStringExtra("src-image");
                Integer imageId = data.getIntExtra("id-image", -1);
                image.setId(imageId);
                image.setSrc(srcImage);

                //Set back data for txtEmail and password
                Glide.with(this)
                        .load(srcImage)
                        .into(avtUser);
            } else {
                // DetailActivity fail
            }
        }
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
        user.setImageId(image.getId());

        boolean updateSuccessful = userDAO.updateUser(user);
        if(updateSuccessful) {
            final Intent data = new Intent();

            // pass data into intent
            data.putExtra("newName", uName);
            data.putExtra("newDob", dob);
            data.putExtra("newAvt", image.getSrc());

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
        chooseImage = findViewById(R.id.chooseImage);
        txtUsername = findViewById(R.id.fullName);
        txtDob = findViewById(R.id.dob);
        avtUser = findViewById(R.id.avtUser);
    }
}
