package com.example.quiz_app.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.User;

public class UserDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addUser(User user) {

        open();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("dob", user.getDob());
        values.put("account_id", user.getAccountId());
        values.put("exp", user.getExp());
        values.put("image_id", user.getImageId());

        db.insert(SQLiteHelper.TABLE_USER, null, values);
        close();
    }

    public boolean updateUser(User user) {
        open();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("dob", user.getDob());
        values.put("exp", user.getExp());
        values.put("image_id", user.getImageId());

        int rowsAffected = db.update(SQLiteHelper.TABLE_USER, values, "id = ?",
                new String[]{String.valueOf(user.getId())});
        close();

        return rowsAffected > 0;
    }

    public User getUserByAccountId(String accountId) {

        open();
        User user = new User();

        String[] selectionArgs = {accountId};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_USER +
                " WHERE account_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setDob(cursor.getString(2));
                user.setExp(cursor.getInt(3));
                user.setImageId(cursor.getInt(5));
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return user;
    }

    public User getUserByLoIdAndStatus(Integer loId, String status) {

        open();
        User user = new User();

        String[] selectionArgs = {loId.toString(), status};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_USER + " u " +
                "INNER JOIN " + SQLiteHelper.TABLE_USER_LO + " ulo" + " ON u.id = ulo.user_id " +
                "INNER JOIN " + SQLiteHelper.TABLE_LEARNING_OBJECT + " lo" + " ON ulo.lo_id = lo.id " +
                "WHERE lo.id = ? AND ulo.status = ? ";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setDob(cursor.getString(2));
                user.setExp(cursor.getInt(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return user;
    }
}
