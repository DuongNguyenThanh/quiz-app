package com.example.quiz_app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.quiz_app.EditUserActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.dal.ImageDAO;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.Image;
import com.example.quiz_app.model.User;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileFragment extends Fragment {

    private final static int REQUEST_CODE_UPDATE = 10001;

    private CircleImageView avtUser;
    private UserDAO userDAO;
    private ImageDAO imageDAO;
    private TextView usernameTextView, dobTextView, expTextView;
    private Button editUser;
    private User user;
    private Image image;
    protected FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        initView(view);
        mAuth = FirebaseAuth.getInstance();

        String uId = mAuth.getCurrentUser().getUid();
        userDAO = new UserDAO(getContext());
        imageDAO = new ImageDAO(getContext());
        user = userDAO.getUserByAccountId(uId);
        image = new Image();

        if (user.getImageId() == -1) {
            avtUser.setImageResource(R.drawable.avt_profile);
        } else {
            image = imageDAO.getImageById(user.getImageId());
            Glide.with(getContext())
                    .load(image.getSrc())
                    .into(avtUser);
        }
        usernameTextView.setText(user.getName());
        dobTextView.setText(user.getDob());
        if (user.getExp() > 100) {
            expTextView.setText("Exp: " + user.getExp() + " - Herald User");
        } else if (user.getExp() > 1000) {
            expTextView.setText("Exp: " + user.getExp() + " - Guardian User");
        } else if (user.getExp() > 10000) {
            expTextView.setText("Exp: " + user.getExp() + " - Crusader User");
        } else if (user.getExp() > 100000) {
            expTextView.setText("Exp: " + user.getExp() + " - Archon User");
        } else if (user.getExp() > 1000000) {
            expTextView.setText("Exp: " + user.getExp() + " - Legend User");
        } else if (user.getExp() > 10000000) {
            expTextView.setText("Exp: " + user.getExp() + " - Ancient User");
        } else if (user.getExp() > 100000000) {
            expTextView.setText("Exp: " + user.getExp() + " - Divine User");
        } else if (user.getExp() > 1000000000) {
            expTextView.setText("Exp: " + user.getExp() + " - Immortal User");
        }

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditUserActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("user-id", user.getId());
                intent.putExtra("avt-src", image.getSrc());
                startActivityForResult(intent, REQUEST_CODE_UPDATE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_UPDATE) {
            if(resultCode == Activity.RESULT_OK) {
                // take data from Intent
                final String nName = data.getStringExtra("newName");
                final String nDob = data.getStringExtra("newDob");
                final String nAvt = data.getStringExtra("newAvt");

                //Set back data for txtEmail and password
                usernameTextView.setText(nName);
                dobTextView.setText(nDob);
                Glide.with(getContext())
                        .load(nAvt)
                        .into(avtUser);
            } else {
                // DetailActivity fail
            }
        }
    }

    private void initView(View view) {

        usernameTextView = view.findViewById(R.id.username);
        dobTextView = view.findViewById(R.id.dob);
        expTextView = view.findViewById(R.id.exp);
        editUser = view.findViewById(R.id.editProfile);
        avtUser = view.findViewById(R.id.avt_user);
    }
}
