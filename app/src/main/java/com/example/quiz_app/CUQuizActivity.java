package com.example.quiz_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_app.model.Answer;
import com.example.quiz_app.model.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CUQuizActivity extends AppCompatActivity {

    private EditText editQuizQuestion, editAnswerA, editAnswerB, editAnswerC, editAnswerD;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button cancelQuiz, updateQuiz, saveQuiz;
    private List<Answer> answers;
    private Answer answerA;
    private Answer answerB ;
    private Answer answerC;
    private Answer answerD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cu_quiz);

        initView();

        Quiz quizInfo = (Quiz) getIntent().getSerializableExtra("quiz-info");
        if (Objects.nonNull(quizInfo)) {

            saveQuiz.setEnabled(false);
            updateQuiz.setEnabled(true);
            editQuizQuestion.setText(quizInfo.getQuizQuestion());
            editAnswerA.setText(quizInfo.getAnswers().get(0).getContent());
            editAnswerB.setText(quizInfo.getAnswers().get(1).getContent());
            editAnswerC.setText(quizInfo.getAnswers().get(2).getContent());
            editAnswerD.setText(quizInfo.getAnswers().get(3).getContent());
            if (quizInfo.getAnswers().get(0).getTrue() == 1) {
                rbA.setChecked(true);
            }
            if (quizInfo.getAnswers().get(1).getTrue() == 1) {
                rbB.setChecked(true);
            }
            if (quizInfo.getAnswers().get(2).getTrue() == 1) {
                rbC.setChecked(true);
            }
            if (quizInfo.getAnswers().get(3).getTrue() == 1) {
                rbD.setChecked(true);
            }
        }

        answers = new ArrayList<>();

        cancelQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isTrueA = 0;
                int isTrueB = 0;
                int isTrueC = 0;
                int isTrueD = 0;

                // Validate
                if (TextUtils.isEmpty(editQuizQuestion.getText())) {
                    Toast.makeText(CUQuizActivity.this, "Quiz question can not be empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(editAnswerA.getText())) {
                    Toast.makeText(CUQuizActivity.this, "Answer can not be empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(editAnswerB.getText())) {
                    Toast.makeText(CUQuizActivity.this, "Answer can not be empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(editAnswerC.getText())) {
                    Toast.makeText(CUQuizActivity.this, "Answer can not be empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(editAnswerD.getText())) {
                    Toast.makeText(CUQuizActivity.this, "Answer can not be empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (rbA.isChecked()) {
                    isTrueA = 1;
                } else if (rbB.isChecked()) {
                    isTrueB = 1;
                } else if (rbC.isChecked()) {
                    isTrueC = 1;
                } else if (rbD.isChecked()) {
                    isTrueD = 1;
                } else {
                    Toast.makeText(CUQuizActivity.this, "Choose one right answer", Toast.LENGTH_LONG).show();
                    return;
                }
                answerA = new Answer(editAnswerA.getText().toString(), isTrueA);
                answers.add(answerA);
                answerB = new Answer(editAnswerB.getText().toString(), isTrueB);
                answers.add(answerB);
                answerC = new Answer(editAnswerC.getText().toString(), isTrueC);
                answers.add(answerC);
                answerD = new Answer(editAnswerD.getText().toString(), isTrueD);
                answers.add(answerD);

                Quiz quiz = new Quiz(editQuizQuestion.getText().toString(), 100, answers);
                final Intent data = new Intent();

                // pass data into intent
                data.putExtra("quiz", quiz);

                // set resultCode is Activity.RESULT_OK
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

        updateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isTrueA = 0;
                int isTrueB = 0;
                int isTrueC = 0;
                int isTrueD = 0;

                if (rbA.isChecked()) {
                    isTrueA = 1;
                } else if (rbB.isChecked()) {
                    isTrueB = 1;
                } else if (rbC.isChecked()) {
                    isTrueC = 1;
                } else if (rbD.isChecked()) {
                    isTrueD = 1;
                }

                answerA = new Answer(editAnswerA.getText().toString(), isTrueA);
                answers.add(answerA);
                answerB = new Answer(editAnswerB.getText().toString(), isTrueB);
                answers.add(answerB);
                answerC = new Answer(editAnswerC.getText().toString(), isTrueC);
                answers.add(answerC);
                answerD = new Answer(editAnswerD.getText().toString(), isTrueD);
                answers.add(answerD);

                Quiz quiz = new Quiz(editQuizQuestion.getText().toString(), 100, answers);

                int quizPos = getIntent().getIntExtra("quiz-pos", -1);
                final Intent data = new Intent();

                // pass data into intent
                data.putExtra("quiz", quiz);
                data.putExtra("quiz-position", quizPos);

                // set resultCode is Activity.RESULT_OK
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }

    private void initView() {

        editQuizQuestion = findViewById(R.id.editQuizQuestion);
        editAnswerA = findViewById(R.id.editAnswerA);
        editAnswerB = findViewById(R.id.editAnswerB);
        editAnswerC = findViewById(R.id.editAnswerC);
        editAnswerD = findViewById(R.id.editAnswerD);
        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);
        cancelQuiz = findViewById(R.id.cancelCreateQuiz);
        saveQuiz = findViewById(R.id.saveQuiz);
        updateQuiz = findViewById(R.id.updateQuiz);
        updateQuiz.setEnabled(false);
    }
}
