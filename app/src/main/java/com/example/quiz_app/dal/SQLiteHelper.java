package com.example.quiz_app.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "quiz_db";
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
        String tableUser = "CREATE TABLE " + TABLE_USER + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(256), " +
                "dob DATE, " +
                "account_id VARCHAR(256) NOT NULL" +
                ")";

        String tableCategory = "CREATE TABLE " + TABLE_CATEGORY + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(256) " +
                ")";

        String tableLo = "CREATE TABLE " + TABLE_LEARNING_OBJECT + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(256), " +
                "avatar VARCHAR(256), " +
                "category_id VARCHAR(256), " +
                "FOREIGN KEY('category_id') REFERENCES " + TABLE_CATEGORY + "('id') " +
                ")";

        String tableUserLo = "CREATE TABLE " + TABLE_USER_LO + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT, " +
                "current_exp INT, " +
                "lo_id INT, " +
                "user_id INT, " +
                "FOREIGN KEY('lo_id') REFERENCES " + TABLE_LEARNING_OBJECT + "('id'), " +
                "FOREIGN KEY('user_id') REFERENCES " + TABLE_USER + "('id') " +
                ")";

        String tableQuiz = "CREATE TABLE " + TABLE_QUIZ + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT, " +
                "quiz_question TEXT, " +
                "exp INT, " +
                "lo_id INT, " +
                "FOREIGN KEY('lo_id') REFERENCES " + TABLE_LEARNING_OBJECT + "('id') " +
                ")";

        String tableAnswer = "CREATE TABLE " + TABLE_ANSWER + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT, " +
                "content TEXT, " +
                "is_true BOOLEAN, " +
                "quiz_id INT, " +
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

    //Create insert method

    //Create getUserProfile Method
}
