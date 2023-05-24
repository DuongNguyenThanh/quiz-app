package com.example.quiz_app.model;

public class Answer extends BaseModel {

    private String content;
    private Integer isTrue, quizId;

    public Answer() {
    }

    public Answer(String content, Integer isTrue) {
        this.content = content;
        this.isTrue = isTrue;
    }

    public Answer(String content, Integer isTrue, Integer quizId) {
        this.content = content;
        this.isTrue = isTrue;
        this.quizId = quizId;
    }

    public Answer(Integer id, String content, Integer isTrue, Integer quizId) {
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

    public Integer getTrue() {
        return isTrue;
    }

    public void setTrue(Integer aTrue) {
        isTrue = aTrue;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
