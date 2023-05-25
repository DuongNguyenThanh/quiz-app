package com.example.quiz_app.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.Image;
import com.example.quiz_app.model.enumtype.ImageTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public ImageDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Image getImageById(Integer id) {

        open();
        Image image = new Image();

        String[] selectionArgs = {id.toString()};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_IMAGE +
                " WHERE id = ?";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {

            String src = cursor.getString(1);
            String type = cursor.getString(2);
            image = new Image(id, src, type);
        }
        cursor.close();
        close();
        return image;
    }

    public List<Image> getImageByImageType(String type) {

        open();
        List<Image> images = new ArrayList<>();

        String[] selectionArgs = {type};
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_IMAGE +
                " WHERE type = ?";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String src = cursor.getString(1);
                Image image = new Image(id, src, type);
                images.add(image);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return images;
    }

    public boolean isDataExists() {

        open();
        String selectQuery = "SELECT COUNT(*) FROM " + SQLiteHelper.TABLE_IMAGE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public void createInitialData() {

        open();
        // Add data image
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (id, src, type) VALUES (0, 'https://images.spiderum.com/sp-avatar/3155ad603a6111ec9d6b4f0d35088d14.jpeg', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/924/924915.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/706/706830.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/706/706807.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/921/921124.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/1154/1154448.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/4322/4322992.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/1326/1326405.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/547/547413.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/1308/1308845.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/1154/1154473.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/706/706831.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/3940/3940403.png', '" + ImageTypeEnum.AVATAR.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/4322/4322991.png', '" + ImageTypeEnum.AVATAR.name() +"')");

        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/1973/1973739.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/3411/3411956.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/4710/4710644.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/4717/4717970.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/2490/2490315.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/3299/3299912.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/9104/9104906.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/2061/2061062.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/2892/2892524.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/2314/2314736.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/5190/5190335.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/6259/6259201.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/748/748893.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/2821/2821637.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/2103/2103607.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/6462/6462711.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/7416/7416941.png', '" + ImageTypeEnum.QUIZ.name() +"')");
        db.execSQL("INSERT INTO " + SQLiteHelper.TABLE_IMAGE +
                " (src, type) VALUES ('https://cdn-icons-png.flaticon.com/512/5660/5660272.png', '" + ImageTypeEnum.QUIZ.name() +"')");
    }
}
