package com.example.quiz_app.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "quiz.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String TABLE_USER_LO = "user_lo";
    public static final String TABLE_LEARNING_OBJECT = "learning_object";
    public static final String TABLE_QUIZ = "quiz";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_ANSWER = "answer";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create table
        String tableUser = "CREATE TABLE " + TABLE_USER + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "dob TEXT, " +
                "account_id TEXT NOT NULL" +
                ")";

        String tableCategory = "CREATE TABLE " + TABLE_CATEGORY +  " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "img_resource TEXT " +
                ")";

        String tableLo = "CREATE TABLE " + TABLE_LEARNING_OBJECT + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "avatar TEXT, " +
                "category_id TEXT, " +
                "FOREIGN KEY('category_id') REFERENCES " + TABLE_CATEGORY + "('id') " +
                ")";

        String tableUserLo = "CREATE TABLE " + TABLE_USER_LO + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "current_exp INTEGER, " +
                "lo_id INTEGER, " +
                "user_id INTEGER, " +
                "FOREIGN KEY('lo_id') REFERENCES " + TABLE_LEARNING_OBJECT + "('id'), " +
                "FOREIGN KEY('user_id') REFERENCES " + TABLE_USER + "('id') " +
                ")";

        String tableQuiz = "CREATE TABLE " + TABLE_QUIZ + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quiz_question TEXT, " +
                "exp INTEGER, " +
                "lo_id INTEGER, " +
                "FOREIGN KEY('lo_id') REFERENCES " + TABLE_LEARNING_OBJECT + "('id') " +
                ")";

        String tableAnswer = "CREATE TABLE " + TABLE_ANSWER + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "content TEXT, " +
                "is_true INTEGER, " +
                "quiz_id INTEGER, " +
                "FOREIGN KEY('quiz_id') REFERENCES " + TABLE_QUIZ + "('id') " +
                ")";

        sqLiteDatabase.execSQL(tableUser);
        sqLiteDatabase.execSQL(tableCategory);
        sqLiteDatabase.execSQL(tableLo);
        sqLiteDatabase.execSQL(tableUserLo);
        sqLiteDatabase.execSQL(tableQuiz);
        sqLiteDatabase.execSQL(tableAnswer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Drop existing table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LEARNING_OBJECT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_LO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWER);
        onCreate(sqLiteDatabase);
    }
}
