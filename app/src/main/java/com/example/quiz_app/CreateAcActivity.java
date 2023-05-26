package com.example.quiz_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.adapter.LearningObjectAdapter;
import com.example.quiz_app.dal.LearningObjectDAO;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.model.LearningObject;
import com.example.quiz_app.model.User;
import com.example.quiz_app.model.enumtype.UserLoStatusEnum;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class CreateAcActivity extends AppCompatActivity implements LearningObjectAdapter.LearningObjectListener {

    private ImageView rollBack;
    private LearningObjectAdapter adapter;
    private RecyclerView createRecyclerView;
    private LearningObjectDAO mLearningObjectDAO;
    private User user;
    private UserDAO mUserDAO;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);

        initView();

        mAuth = FirebaseAuth.getInstance();
        String accountId = mAuth.getCurrentUser().getUid();
        mUserDAO = new UserDAO(this);
        user = mUserDAO.getUserByAccountId(accountId);

        adapter = new LearningObjectAdapter(this);
        mLearningObjectDAO = new LearningObjectDAO(this);

        List<LearningObject> learningObjects = mLearningObjectDAO.getLearningObjectByStatusAndUserId(UserLoStatusEnum.CREATE_LO.name(), user.getId());
        adapter.setLstLearningObject(learningObjects);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        createRecyclerView.setLayoutManager(manager);
        createRecyclerView.setAdapter(adapter);
        adapter.setLearningObjectListener(this);

        rollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {

        createRecyclerView = findViewById(R.id.createdRecyclerView);
        rollBack = findViewById(R.id.rollBack);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        List<LearningObject> learningObjects = mLearningObjectDAO.getLearningObjectByStatusAndUserId(UserLoStatusEnum.CREATE_LO.name(), user.getId());
        adapter.setLstLearningObject(learningObjects);
    }
}
