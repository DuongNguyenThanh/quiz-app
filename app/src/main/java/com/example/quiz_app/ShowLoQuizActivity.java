package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_app.adapter.LearningObjectAdapter;
import com.example.quiz_app.dal.LearningObjectDAO;
import com.example.quiz_app.model.Category;
import com.example.quiz_app.model.LearningObject;

import java.util.List;

public class ShowLoQuizActivity extends AppCompatActivity implements LearningObjectAdapter.LearningObjectListener {

    private ImageView rollBack;
    private RecyclerView quizObjectRecyclerView;
    private LearningObjectDAO mLearningObjectDAO;
    private LearningObjectAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lo);

        initView();
        Category category = (Category) getIntent().getSerializableExtra("category");
        Integer categoryId = getIntent().getIntExtra("category-id", 0);
        category.setId(categoryId);

        mLearningObjectDAO = new LearningObjectDAO(this);
        List<LearningObject> learningObjects = mLearningObjectDAO.getLearningObjectByCategory(category);
        adapter = new LearningObjectAdapter(this);
        adapter.setLstLearningObject(learningObjects);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        quizObjectRecyclerView.setLayoutManager(manager);
        quizObjectRecyclerView.setAdapter(adapter);
        adapter.setLearningObjectListener(this);

        rollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {

        rollBack = findViewById(R.id.rollBack);
        quizObjectRecyclerView = findViewById(R.id.quizObjectRecyclerView);
    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent(ShowLoQuizActivity.this,
                QuizActivity.class);
        intent.putExtra("lo-id", adapter.getLearningObject(position).getId());
        startActivity(intent);
    }
}
