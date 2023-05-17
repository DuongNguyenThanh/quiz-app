package com.example.quiz_app.model;

public class Quiz extends BaseModel {

    private String quizQuestion;
    private Integer exp, loId;

    public Quiz() {
    }

    public Quiz(String quizQuestion, Integer exp, Integer loId) {
        this.quizQuestion = quizQuestion;
        this.exp = exp;
        this.loId = loId;
    }

    public Quiz(Integer id, String quizQuestion, Integer exp, Integer loId) {
        super(id);
        this.quizQuestion = quizQuestion;
        this.exp = exp;
        this.loId = loId;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getLoId() {
        return loId;
    }

    public void setLoId(Integer loId) {
        this.loId = loId;
    }
}
