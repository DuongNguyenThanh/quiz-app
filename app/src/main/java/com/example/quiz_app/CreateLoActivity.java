package com.example.quiz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quiz_app.adapter.CategorySpinnerAdapter;
import com.example.quiz_app.adapter.QuizAdapter;
import com.example.quiz_app.dal.CategoryDAO;
import com.example.quiz_app.model.Category;
import com.example.quiz_app.model.Quiz;
import com.example.quiz_app.util.ImageTypeEnum;

import java.util.List;

public class CreateLoActivity extends AppCompatActivity implements QuizAdapter.QuizListener {

    private final static int REQUEST_CODE_IMAGE = 10001;
    private final static int REQUEST_CODE_ADD_QUIZ = 10002;
    private final static int REQUEST_CODE_EDIT_QUIZ = 10003;

    private EditText quizNameTxt;
    private Spinner cateSpinner;
    private Button btnAddQuiz, btnChooseImage, cancelCreateLo, saveLo;
    private ImageView avtImage;
    private RecyclerView quizRecyclerView;
    private CategoryDAO mCategoryDAO;
    private QuizAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lo);

        initView();

        // Category spinner
        CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(this);
        mCategoryDAO = new CategoryDAO(this);

        List<Category> categoryList = mCategoryDAO.getAllCategories();
        categorySpinnerAdapter.setLstCategory(categoryList);

        cateSpinner.setAdapter(categorySpinnerAdapter);

        // Quiz recycle view
        adapter = new QuizAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        quizRecyclerView.setLayoutManager(manager);
        quizRecyclerView.setAdapter(adapter);
        adapter.setQuizListener(this);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateLoActivity.this, ImageActivity.class);
                intent.putExtra("image-type", ImageTypeEnum.QUIZ.name());
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        cancelCreateLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateLoActivity.this, CUQuizActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_QUIZ);
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

                //Set back data for txtEmail and password
                Glide.with(this)
                        .load(srcImage)
                        .into(avtImage);
            } else {
                // DetailActivity fail
            }
        }

        if(requestCode == REQUEST_CODE_ADD_QUIZ) {
            if(resultCode == Activity.RESULT_OK) {
                // take data from Intent
                final Quiz quiz = (Quiz) data.getSerializableExtra("quiz");

                //Set back data
                adapter.addQuiz(quiz);
                System.out.println("not good");
            } else {
                // DetailActivity fail
            }
        }

        if(requestCode == REQUEST_CODE_EDIT_QUIZ) {
            if(resultCode == Activity.RESULT_OK) {
                // take data from Intent
                final Quiz quiz = (Quiz) data.getSerializableExtra("quiz");
                final int quizPos = data.getIntExtra("quiz-position", -1);
                //Set back data
                adapter.updateQuiz(quizPos, quiz);
            } else {
                // DetailActivity fail
            }
        }
    }

    private void initView() {

        cateSpinner = findViewById(R.id.categorySpinner);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        quizNameTxt = findViewById(R.id.quizNameTxt);
        btnAddQuiz = findViewById(R.id.btnAddQuiz);
        avtImage = findViewById(R.id.avtImage);
        cancelCreateLo = findViewById(R.id.cancelCreateLO);
        saveLo = findViewById(R.id.saveLO);
        quizRecyclerView = findViewById(R.id.quizRecyclerView);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(CreateLoActivity.this, CUQuizActivity.class);
        intent.putExtra("quiz-info", adapter.getQuiz(position));
        intent.putExtra("quiz-pos", position);
        startActivityForResult(intent, REQUEST_CODE_EDIT_QUIZ);
    }
}