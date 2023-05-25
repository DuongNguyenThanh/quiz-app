package com.example.quiz_app.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.Answer;
import com.example.quiz_app.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public QuizDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Quiz> getQuizzesByLoId(Integer loId) {

        open();
        List<Quiz> quizzes = new ArrayList<>();

        String[] selectionArgs = {loId.toString()};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_QUIZ +
                " WHERE lo_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                Integer quizId = cursor.getInt(0);
                String quizQuestion = cursor.getString(1);
                Integer exp = cursor.getInt(2);

                // Find Answers by quizId
                List<Answer> answers = new ArrayList<>();
                String[] selectionArgsAnswer = {quizId.toString()};
                String selectQueryAnswer = "SELECT * FROM " + SQLiteHelper.TABLE_ANSWER +
                        " WHERE quiz_id = ?";
                Cursor cursorAnswer = db.rawQuery(selectQueryAnswer, selectionArgsAnswer);
                cursorAnswer.moveToFirst();

                if (cursorAnswer.moveToFirst()) {
                    do {
                        Integer answerId = cursorAnswer.getInt(0);
                        String content = cursorAnswer.getString(1);
                        Integer isTrue = cursorAnswer.getInt(2);

                        Answer answer = new Answer(answerId, content, isTrue, quizId);
                        answers.add(answer);
                    } while (cursorAnswer.moveToNext());
                }
                cursorAnswer.close();

                Quiz quiz = new Quiz(quizId, quizQuestion, exp, loId, answers);
                quizzes.add(quiz);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return quizzes;
    }
}
