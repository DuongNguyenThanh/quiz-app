package com.example.quiz_app.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.UserLo;
import com.example.quiz_app.model.enumtype.UserLoStatusEnum;

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

    public UserLo getUserLoByUserIdAndLoIdAndStatus(Integer uId, Integer loId, String status) {

        open();
        UserLo userLo = new UserLo();

        String[] selectionArgs = {uId.toString(), loId.toString(), status};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_USER_LO +
                " WHERE user_id = ? AND lo_id = ? AND status = ? ";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            userLo.setId(cursor.getInt(0));
            userLo.setCurrentExp(cursor.getInt(1));
            userLo.setStatus(cursor.getString(2));
            userLo.setUserId(uId);
            userLo.setLoId(loId);
        }
        cursor.close();
        close();
        return userLo;
    }

    public UserLo getUserLoByUserIdAndLoId(Integer uId, Integer loId) {

        open();
        UserLo userLo = new UserLo();

        String[] selectionArgs = {uId.toString(), loId.toString()};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_USER_LO +
                " WHERE user_id = ? AND lo_id = ? AND status != '" + UserLoStatusEnum.CREATE_LO.name() + "' ";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            userLo.setId(cursor.getInt(0));
            userLo.setCurrentExp(cursor.getInt(1));
            userLo.setStatus(cursor.getString(2));
            userLo.setUserId(uId);
            userLo.setLoId(loId);
        }
        cursor.close();
        close();
        return userLo;
    }

    public boolean updateUserLo(UserLo userLo) {

        open();
        ContentValues values = new ContentValues();
        values.put("current_exp", userLo.getCurrentExp());
        values.put("status", userLo.getStatus());
        values.put("lo_id", userLo.getLoId());
        values.put("user_id", userLo.getUserId());

        int rowsAffected = db.update(SQLiteHelper.TABLE_USER_LO, values, "id = ? ",
                new String[]{String.valueOf(userLo.getId())});
        close();

        return rowsAffected > 0;
    }

    public Integer addUserLo(UserLo userLo) {

        Integer userLoId = -1;
        open();
        ContentValues values = new ContentValues();
        values.put("current_exp", userLo.getCurrentExp());
        values.put("status", userLo.getStatus());
        values.put("lo_id", userLo.getLoId());
        values.put("user_id", userLo.getUserId());

        try {
            db.insertOrThrow(SQLiteHelper.TABLE_USER_LO, null, values);

            String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_USER_LO +
                    " ORDER BY id DESC LIMIT 1";
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();

            if (cursor.moveToFirst()) {
                userLoId = cursor.getInt(0);
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        close();
        return userLoId;
    }
}
