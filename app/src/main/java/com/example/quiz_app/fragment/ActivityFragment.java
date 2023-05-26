package com.example.quiz_app.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.quiz_app.CreateAcActivity;
import com.example.quiz_app.CreateLoActivity;
import com.example.quiz_app.DoingAcActivity;
import com.example.quiz_app.DoneAcActivity;
import com.example.quiz_app.R;

public class ActivityFragment extends Fragment {

    private CardView createBtn, doneBtn, doingBtn;
    private Button btnCreateQuiz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createBtn = view.findViewById(R.id.createBtn);
        doneBtn = view.findViewById(R.id.doneBtn);
        doingBtn = view.findViewById(R.id.doingBtn);
        btnCreateQuiz = view.findViewById(R.id.btnAddQuiz);

        btnCreateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        CreateLoActivity.class);
                startActivity(intent);
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        CreateAcActivity.class);
                startActivity(intent);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        DoneAcActivity.class);
                startActivity(intent);
            }
        });

        doingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),
                        DoingAcActivity.class);
                startActivity(intent);
            }
        });
    }
}
