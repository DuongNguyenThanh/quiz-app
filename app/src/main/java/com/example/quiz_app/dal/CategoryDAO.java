package com.example.quiz_app.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public CategoryDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean isDataExists() {

        open();
        String selectQuery = "SELECT COUNT(*) FROM " + SQLiteHelper.TABLE_CATEGORY;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void createInitialData() {

        open();
        // Add data category
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_CATEGORY +
                " (name, img_resource) VALUES ('Mathematics', 'https://cdn-icons-png.flaticon.com/512/3965/3965108.png')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_CATEGORY +
                " (name, img_resource) VALUES ('Language', 'https://cdn-icons-png.flaticon.com/512/3898/3898082.png')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_CATEGORY +
                " (name, img_resource) VALUES ('Science', 'https://cdn-icons-png.flaticon.com/512/732/732598.png')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_CATEGORY +
                " (name, img_resource) VALUES ('History', 'https://i.pinimg.com/originals/06/d2/cf/06d2cfa5cd7f8fbe8e94ef5d75496a75.png')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_CATEGORY +
                " (name, img_resource) VALUES ('Drama', 'https://cdn-icons-png.flaticon.com/512/1048/1048962.png')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_CATEGORY +
                " (name, img_resource) VALUES ('Anime', 'https://cdn.icon-icons.com/icons2/1736/PNG/512/4043233-anime-away-face-no-nobody-spirited_113254.png')");
    }

    public List<Category> getAllCategories() {

        open();
        List<Category> cateList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + SQLiteHelper.TABLE_CATEGORY;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String img = cursor.getString(2);
                Category category = new Category(id, name, img);
                cateList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return cateList;
    }
}
