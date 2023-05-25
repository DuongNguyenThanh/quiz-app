package com.example.quiz_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.QuizActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.adapter.LearningObjectAdapter;
import com.example.quiz_app.dal.LearningObjectDAO;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.LearningObject;
import com.example.quiz_app.model.User;
import com.example.quiz_app.model.enumtype.UserLoStatusEnum;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DoingActivityFragment extends Fragment implements LearningObjectAdapter.LearningObjectListener {

    private LearningObjectAdapter adapter;
    private RecyclerView recyclerView;
    private LearningObjectDAO mLearningObjectDAO;
    private User user;
    private UserDAO mUserDAO;
    protected FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_content, container, false);

        mAuth = FirebaseAuth.getInstance();
        String accountId = mAuth.getCurrentUser().getUid();
        mUserDAO = new UserDAO(getContext());
        user = mUserDAO.getUserByAccountId(accountId);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.activityContentRecyclerView);
        adapter = new LearningObjectAdapter(getContext());
        mLearningObjectDAO = new LearningObjectDAO(getContext());

        List<LearningObject> learningObjects = mLearningObjectDAO.getLearningObjectByStatusAndUserId(UserLoStatusEnum.DOING.name(), user.getId());
        adapter.setLstLearningObject(learningObjects);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setLearningObjectListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("lo-id", adapter.getLearningObject(position).getId());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<LearningObject> learningObjects = mLearningObjectDAO.getLearningObjectByStatusAndUserId(UserLoStatusEnum.DOING.name(), user.getId());
        adapter.setLstLearningObject(learningObjects);
    }
}
