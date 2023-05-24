package com.example.quiz_app.model;

public class Image extends BaseModel {

    private String src, type;

    public Image() {
    }

    public Image(String src, String type) {
        this.src = src;
        this.type = type;
    }

    public Image(Integer id, String src, String type) {
        super(id);
        this.src = src;
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
