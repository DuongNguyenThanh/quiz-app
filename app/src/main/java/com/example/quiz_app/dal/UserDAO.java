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
        values.put("account_id", user.getAccountId());

        db.insert(SQLiteHelper.TABLE_USER, null, values);
        close();
    }

//    public void updateBook(Book book) {
//        open();
//        ContentValues values = new ContentValues();
//        values.put(SQLiteHelper.COLUMN_NAME, book.getName());
//        values.put(SQLiteHelper.COLUMN_AUTHOR, book.getAuthor());
//        values.put(SQLiteHelper.COLUMN_RELEASE_DATE, book.getReleaseDate());
//        values.put(SQLiteHelper.COLUMN_PUBLISHER, book.getPublisher());
//        values.put(SQLiteHelper.COLUMN_PRICE, book.getPrice());
//
//        db.update(SQLiteHelper.TABLE_NAME, values, SQLiteHelper.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(book.getId())});
//        close();
//    }

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
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return user;
    }
}
