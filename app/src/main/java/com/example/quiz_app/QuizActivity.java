package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_app.dal.QuizDAO;
import com.example.quiz_app.dal.UserDAO;
import com.example.quiz_app.dal.UserLoDAO;
import com.example.quiz_app.dal.UserQuizDAO;
import com.example.quiz_app.model.Answer;
import com.example.quiz_app.model.Quiz;
import com.example.quiz_app.model.User;
import com.example.quiz_app.model.UserLo;
import com.example.quiz_app.model.UserQuiz;
import com.example.quiz_app.model.enumtype.UserLoStatusEnum;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView quizQuestion, optionA, optionB, optionC, optionD, questionCounter, countDownTime;
    private Button cancelBtn, nextBtn;
    private QuizDAO mQuizDAO;
    private UserDAO mUserDAO;
    private UserLoDAO mUserLoDAO;
    private UserQuizDAO mUserQuizDAO;
    private User user;
    private UserLo userLo;
    private List<Quiz> quizzes;
    private Quiz quiz;
    private List<Answer> answers;
    private Integer index, quizzesSize, expResult, countQuizTrue;
    private CountDownTimer timer;
    protected FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();
        mAuth = FirebaseAuth.getInstance();
        user = new User();
        mUserDAO = new UserDAO(this);
        String accountId = mAuth.getCurrentUser().getUid();
        user = mUserDAO.getUserByAccountId(accountId);
        userLo = new UserLo();

        mUserLoDAO = new UserLoDAO(this);
        mUserQuizDAO = new UserQuizDAO(this);
        mQuizDAO = new QuizDAO(this);

        Integer loId = getIntent().getIntExtra("lo-id", -1);

        // Add User Lo - status: DOING
        userLo = mUserLoDAO.getUserLoByUserIdAndLoId(user.getId(), loId);
        if (userLo.getId() == null) {
            userLo = new UserLo(0, UserLoStatusEnum.DOING.name(), loId, user.getId());
            Integer uLoId = mUserLoDAO.addUserLo(userLo);
            userLo.setId(uLoId);
        }

        quizzes = mQuizDAO.getQuizzesByLoId(loId);
        quizzesSize = quizzes.size();
        // Random quiz
        Collections.shuffle(quizzes);

        quiz = quizzes.get(index);
        answers = quiz.getAnswers();
        // Random answer
        Collections.shuffle(answers);
        questionCounter.setText((index+1) + "/" + quizzesSize);
        quizQuestion.setText(quiz.getQuizQuestion());
        optionA.setText(answers.get(0).getContent());
        optionB.setText(answers.get(1).getContent());
        optionC.setText(answers.get(2).getContent());
        optionD.setText(answers.get(3).getContent());

        resetTimer();
        timer.start();

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timerCancel();
                if (answers.get(0).getTrue() == 1) {
                    optionA.setBackgroundResource(R.drawable.option_right);
                    updateInfoIfTrue();
                }
                else {
                    optionA.setBackgroundResource(R.drawable.option_wrong);
                    if (answers.get(1).getTrue() == 1) {
                        optionB.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(2).getTrue() == 1) {
                        optionC.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(3).getTrue() == 1) {
                        optionD.setBackgroundResource(R.drawable.option_right);
                    }
                }
                disableAnswer();
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timerCancel();
                if (answers.get(1).getTrue() == 1) {
                    optionB.setBackgroundResource(R.drawable.option_right);
                    updateInfoIfTrue();
                }
                else {
                    optionB.setBackgroundResource(R.drawable.option_wrong);
                    if (answers.get(0).getTrue() == 1) {
                        optionA.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(2).getTrue() == 1) {
                        optionC.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(3).getTrue() == 1) {
                        optionD.setBackgroundResource(R.drawable.option_right);
                    }
                }
                disableAnswer();
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timerCancel();
                if (answers.get(2).getTrue() == 1) {
                    optionC.setBackgroundResource(R.drawable.option_right);
                    updateInfoIfTrue();
                }
                else {
                    optionC.setBackgroundResource(R.drawable.option_wrong);
                    if (answers.get(0).getTrue() == 1) {
                        optionA.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(1).getTrue() == 1) {
                        optionB.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(3).getTrue() == 1) {
                        optionD.setBackgroundResource(R.drawable.option_right);
                    }
                }
                disableAnswer();
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timerCancel();
                if (answers.get(3).getTrue() == 1) {
                    optionD.setBackgroundResource(R.drawable.option_right);
                    updateInfoIfTrue();
                }
                else {
                    optionD.setBackgroundResource(R.drawable.option_wrong);
                    if (answers.get(0).getTrue() == 1) {
                        optionA.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(1).getTrue() == 1) {
                        optionB.setBackgroundResource(R.drawable.option_right);
                    }
                    if (answers.get(2).getTrue() == 1) {
                        optionC.setBackgroundResource(R.drawable.option_right);
                    }
                }
                disableAnswer();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer.start();
                index += 1;
                if (index == quizzesSize) {

                    userLo.setStatus(UserLoStatusEnum.COMPLETE.name());
                    mUserLoDAO.updateUserLo(userLo);
                    Intent intent = new Intent(QuizActivity.this,
                            ResultActivity.class);
                    intent.putExtra("quizzes-size", quizzesSize);
                    intent.putExtra("count-quiz-true", countQuizTrue);
                    intent.putExtra("exp-result", expResult);
                    startActivity(intent);
                }
                else {
                    quiz = quizzes.get(index);
                    answers = quiz.getAnswers();
                    // Random answer
                    Collections.shuffle(answers);

                    questionCounter.setText((index+1) + "/" + quizzesSize);
                    quizQuestion.setText(quiz.getQuizQuestion());
                    optionA.setText(answers.get(0).getContent());
                    optionB.setText(answers.get(1).getContent());
                    optionC.setText(answers.get(2).getContent());
                    optionD.setText(answers.get(3).getContent());

                    // Reset True-False
                    optionA.setBackgroundResource(R.drawable.custom_edittext_v2);
                    optionB.setBackgroundResource(R.drawable.custom_edittext_v2);
                    optionC.setBackgroundResource(R.drawable.custom_edittext_v2);
                    optionD.setBackgroundResource(R.drawable.custom_edittext_v2);
                    enableAnswer();
                }
            }
        });
    }

    private void initView() {

        quizQuestion = findViewById(R.id.quizQuestion);
        questionCounter = findViewById(R.id.questionCounter);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        cancelBtn = findViewById(R.id.cancelBtn);
        nextBtn = findViewById(R.id.nextBtn);
        countDownTime = findViewById(R.id.countDownTime);

        nextBtn.setEnabled(false);
        index = 0;
        expResult = 0;
        countQuizTrue = 0;
    }

    private void updateInfoIfTrue() {

        countQuizTrue += 1;
        if (mUserQuizDAO.getUserQuizByUserIdAndQuizId(user.getId(), quiz.getId()).getId() == null) {

            // Add UserQuiz if not exist
            UserQuiz userQuiz = new UserQuiz(quiz.getId(), user.getId());
            mUserQuizDAO.addUserQuiz(userQuiz);
            expResult += quiz.getExp();

            // Update current_exp of UserLo
            Integer newCurrentExp = userLo.getCurrentExp() + quiz.getExp();
            userLo.setCurrentExp(newCurrentExp);
            mUserLoDAO.updateUserLo(userLo);

            // Update exp of User
            Integer newExp = user.getExp() + quiz.getExp();
            user.setExp(newExp);
            mUserDAO.updateUser(user);
        }
    }

    private void enableAnswer() {

        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);
        nextBtn.setEnabled(false);
    }

    private void disableAnswer() {

        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);
        nextBtn.setEnabled(true);
    }

    private void resetTimer() {
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                countDownTime.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {

                optionA.setBackgroundResource(R.drawable.option_wrong);
                optionB.setBackgroundResource(R.drawable.option_wrong);
                optionC.setBackgroundResource(R.drawable.option_wrong);
                optionD.setBackgroundResource(R.drawable.option_wrong);
                if (answers.get(0).getTrue() == 1) {
                    optionA.setBackgroundResource(R.drawable.option_right);
                } else if (answers.get(1).getTrue() == 1) {
                    optionB.setBackgroundResource(R.drawable.option_right);
                } else if (answers.get(2).getTrue() == 1) {
                    optionC.setBackgroundResource(R.drawable.option_right);
                } else if (answers.get(3).getTrue() == 1) {
                    optionD.setBackgroundResource(R.drawable.option_right);
                }
                disableAnswer();
            }
        };
    }

    private void timerCancel() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
