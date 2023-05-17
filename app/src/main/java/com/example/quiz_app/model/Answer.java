package com.example.quiz_app.model;

public class Answer extends BaseModel {

    private String content;
    private Boolean isTrue;
    private Integer quizId;

    public Answer() {
    }

    public Answer(String content, Boolean isTrue, Integer quizId) {
        this.content = content;
        this.isTrue = isTrue;
        this.quizId = quizId;
    }

    public Answer(Integer id, String content, Boolean isTrue, Integer quizId) {
        super(id);
        this.content = content;
        this.isTrue = isTrue;
        this.quizId = quizId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
