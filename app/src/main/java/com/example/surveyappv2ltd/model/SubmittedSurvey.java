package com.example.surveyappv2ltd.model;

public class SubmittedSurvey {
    int id;
int optionPosition;
    String question;
    String answer;

    public SubmittedSurvey(int id,int optionPosition, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.optionPosition = optionPosition;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getOptionPosition() {
        return optionPosition;
    }

    public void setOptionPosition(int optionPosition) {
        this.optionPosition = optionPosition;
    }
}
