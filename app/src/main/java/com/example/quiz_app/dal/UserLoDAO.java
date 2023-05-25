package com.example.quiz_app.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.UserLo;

public class UserLoDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public UserLoDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addUserLo(UserLo userLo) {

        open();
        ContentValues values = new ContentValues();
        values.put("current_exp", userLo.getCurrentExp());
        values.put("status", userLo.getStatus());
        values.put("lo_id", userLo.getLoId());
        values.put("user_id", userLo.getUserId());

        try {
            db.insertOrThrow(SQLiteHelper.TABLE_USER_LO, null, values);
        } catch(Exception ex) {
            System.out.println(ex);
        }

        close();
    }
}
