package com.example.quiz_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quiz_app.R;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class UserProfileFragment extends Fragment {

    private UserDAO userDAO;
    private TextView usernameTextView;
    protected FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        String uId = mAuth.getCurrentUser().getUid();
        User user = userDAO.getUserByAccountId(uId);

        usernameTextView = view.findViewById(R.id.username);

        usernameTextView.setText(user.getName());

        return view;
    }
}
