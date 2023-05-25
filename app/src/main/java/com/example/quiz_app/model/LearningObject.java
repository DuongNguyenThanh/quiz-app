package com.example.quiz_app.model;

import java.util.List;

public class LearningObject extends BaseModel {

    private String title;
    private Category category;
    private Image image;
    private List<Quiz> quizzes;

    public LearningObject() {
    }

    public LearningObject(String title, Category category, Image image, List<Quiz> quizzes) {
        this.title = title;
        this.category = category;
        this.image = image;
        this.quizzes = quizzes;
    }

    public LearningObject(Integer id, String title, Category category, Image image) {
        super(id);
        this.title = title;
        this.category = category;
        this.image = image;
    }

    public LearningObject(Integer id, String title, Category category, Image image, List<Quiz> quizzes) {
        super(id);
        this.title = title;
        this.category = category;
        this.image = image;
        this.quizzes = quizzes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
