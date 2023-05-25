package com.example.quiz_app.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.UserQuiz;

public class UserQuizDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public UserQuizDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public UserQuiz getUserQuizByUserIdAndQuizId(Integer uId, Integer quizId) {

        open();
        UserQuiz userQuiz = new UserQuiz();

        String[] selectionArgs = {uId.toString(), quizId.toString()};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_USER_QUIZ +
                " WHERE user_id = ? AND quiz_id = ? ";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            userQuiz.setId(cursor.getInt(0));
            userQuiz.setUserId(uId);
            userQuiz.setQuizId(quizId);
        }
        cursor.close();
        close();
        return userQuiz;
    }

    public void addUserQuiz(UserQuiz userQuiz) {

        open();
        ContentValues values = new ContentValues();
        values.put("quiz_id", userQuiz.getQuizId());
        values.put("user_id", userQuiz.getUserId());

        try {
            db.insertOrThrow(SQLiteHelper.TABLE_USER_QUIZ, null, values);
        } catch(Exception ex) {
            System.out.println(ex);
        }
        close();
    }
}
