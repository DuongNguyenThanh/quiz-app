package com.example.quiz_app.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz_app.model.Answer;
import com.example.quiz_app.model.Category;
import com.example.quiz_app.model.Image;
import com.example.quiz_app.model.LearningObject;
import com.example.quiz_app.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class LearningObjectDAO {

    private SQLiteDatabase db;
    private final SQLiteHelper dbHelper;

    public LearningObjectDAO(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<LearningObject> getLearningObjectByCategory(Category category) {

        open();
        List<LearningObject> learningObjects = new ArrayList<>();

        String[] selectionArgs = {category.getId().toString()};
        // Find Learning Object by category id
        String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_LEARNING_OBJECT +
                " WHERE category_id = ?";
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String title = cursor.getString(1);
                Integer imageId = cursor.getInt(3);

                Image image = new Image();
                if (imageId != -1) {
                    // Find Image by id
                    String[] selectionArgsImage = {imageId.toString()};
                    String selectQueryImage = "SELECT * FROM " + SQLiteHelper.TABLE_IMAGE +
                            " WHERE id = ?";
                    Cursor cursorImage = db.rawQuery(selectQueryImage, selectionArgsImage);
                    cursorImage.moveToFirst();

                    if (cursorImage.moveToFirst()) {

                        String src = cursorImage.getString(1);
                        String type = cursorImage.getString(2);
                        image = new Image(imageId, src, type);
                    }
                    cursorImage.close();
                }
                else {
                    image.setId(imageId);
                }

                // Find Quizzes by Learning Object id
                List<Quiz> quizzes = new ArrayList<>();

                String[] selectionArgsQuiz = {id.toString()};
                // Find Learning Object by category id
                String selectQueryQuiz = "SELECT * FROM " + SQLiteHelper.TABLE_QUIZ +
                        " WHERE lo_id = ?";
                Cursor cursorQuiz = db.rawQuery(selectQueryQuiz, selectionArgsQuiz);
                cursorQuiz.moveToFirst();

                if (cursorQuiz.moveToFirst()) {
                    do {
                        Integer quizId = cursorQuiz.getInt(0);
                        String quizQuestion = cursorQuiz.getString(1);
                        Integer exp = cursorQuiz.getInt(2);

                        Quiz quiz = new Quiz(quizId, quizQuestion, exp, id);
                        quizzes.add(quiz);

                    } while (cursorQuiz.moveToNext());
                }
                cursorQuiz.close();

                LearningObject learningObject = new LearningObject(id, title, category, image, quizzes);
                learningObjects.add(learningObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return learningObjects;
    }

    public Integer addLearningObjectWithQuizzes(LearningObject learningObject) {

        Integer learningObjectId = -1;
        open();
        ContentValues values = new ContentValues();
        values.put("title", learningObject.getTitle());
        values.put("category_id", learningObject.getCategory().getId());
        values.put("image_id", learningObject.getImage().getId());
        try {
            db.insertOrThrow(SQLiteHelper.TABLE_LEARNING_OBJECT, null, values);

            String selectQuery = "SELECT * FROM " + SQLiteHelper.TABLE_LEARNING_OBJECT +
                    " ORDER BY id DESC LIMIT 1";
            Cursor cursorLo = db.rawQuery(selectQuery, null);
            cursorLo.moveToFirst();

            if (cursorLo.moveToFirst()) {
                learningObjectId = cursorLo.getInt(0);
                for (Quiz quiz : learningObject.getQuizzes()) {
                    ContentValues valuesQuiz = new ContentValues();
                    valuesQuiz.put("quiz_question", quiz.getQuizQuestion());
                    valuesQuiz.put("exp", quiz.getExp());
                    valuesQuiz.put("lo_id", learningObjectId);
                    try {
                        db.insertOrThrow(SQLiteHelper.TABLE_QUIZ, null, valuesQuiz);

                        String selectQueryQuiz = "SELECT * FROM " + SQLiteHelper.TABLE_QUIZ +
                                " ORDER BY id DESC LIMIT 1";
                        Cursor cursorQuiz = db.rawQuery(selectQueryQuiz, null);
                        cursorQuiz.moveToFirst();

                        if (cursorQuiz.moveToFirst()) {
                            Integer quizId = cursorQuiz.getInt(0);
                            for (Answer answer : quiz.getAnswers()) {
                                ContentValues valuesAnswer = new ContentValues();
                                valuesAnswer.put("content", answer.getContent());
                                valuesAnswer.put("is_true", answer.getTrue());
                                valuesAnswer.put("quiz_id", quizId);
                                try {
                                    db.insertOrThrow(SQLiteHelper.TABLE_ANSWER, null, valuesAnswer);
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                }
                            }
                        }
                        cursorQuiz.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
            cursorLo.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
        close();

        return learningObjectId;
    }
}