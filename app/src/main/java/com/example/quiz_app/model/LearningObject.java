package com.example.quiz_app.model;

public class LearningObject extends BaseModel {

    private String title, avatar;
    private Integer categoryId;

    public LearningObject() {
    }

    public LearningObject(String title, String avatar, Integer categoryId) {
        this.title = title;
        this.avatar = avatar;
        this.categoryId = categoryId;
    }

    public LearningObject(Integer id, String title, String avatar, Integer categoryId) {
        super(id);
        this.title = title;
        this.avatar = avatar;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
