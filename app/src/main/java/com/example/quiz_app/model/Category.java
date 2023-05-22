package com.example.quiz_app.model;

public class Category extends BaseModel {

    private String name, img;

    public Category() {
    }

    public Category(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public Category(Integer id, String name, String img) {
        super(id);
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
