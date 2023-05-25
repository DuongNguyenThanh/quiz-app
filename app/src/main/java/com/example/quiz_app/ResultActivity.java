package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView score, earnedExp;
    private Button completeBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();

        Integer quizzesSize = getIntent().getIntExtra("quizzes-size", 10);
        Integer countQuizTrue = getIntent().getIntExtra("count-quiz-true", 10);
        Integer expResult = getIntent().getIntExtra("exp-result", 1000);

        score.setText(countQuizTrue + "/" + quizzesSize);
        earnedExp.setText(expResult.toString());

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        score = findViewById(R.id.score);
        earnedExp = findViewById(R.id.earnedExp);
        completeBtn = findViewById(R.id.completeBtn);
    }
}
