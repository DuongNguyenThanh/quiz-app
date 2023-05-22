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

import com.example.quiz_app.EditUserActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class UserProfileFragment extends Fragment {

    private final static int REQUEST_CODE_UPDATE = 10001;

    private UserDAO userDAO;
    private TextView usernameTextView, dobTextView;
    private Button editUser;
    private User user;
    protected FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        initView(view);
        mAuth = FirebaseAuth.getInstance();

        String uId = mAuth.getCurrentUser().getUid();
        userDAO = new UserDAO(getContext());
        user = userDAO.getUserByAccountId(uId);

        usernameTextView.setText(user.getName());
        dobTextView.setText(user.getDob());

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditUserActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("user-id", user.getId());
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

                //Set back data for txtEmail and password
                usernameTextView.setText(nName);
                dobTextView.setText(nDob);
            } else {
                // DetailActivity fail
            }
        }
    }

    private void initView(View view) {

        usernameTextView = view.findViewById(R.id.username);
        dobTextView = view.findViewById(R.id.dob);
        editUser = view.findViewById(R.id.editProfile);
    }
}
