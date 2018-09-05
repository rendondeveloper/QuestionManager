package com.example.o_lrendon.qquestionmanager;

import java.util.ArrayList;

class Questionary
{
    private int order;
    private String question;
    private ArrayList<String> answerList;
    private int backgroudReosurce;
    private String textButton;

    public Questionary(int order, String question, ArrayList<String> answerList, int backgroudReosurce, String textButton) {
        this.order = order;
        this.question = question;
        this.answerList = answerList;
        this.backgroudReosurce = backgroudReosurce;
        this.textButton = textButton;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<String> answerList) {
        this.answerList = answerList;
    }

    public int getBackgroudReosurce() {
        return backgroudReosurce;
    }

    public void setBackgroudReosurce(int backgroudReosurce) {
        this.backgroudReosurce = backgroudReosurce;
    }

    public String getTextButton() {
        return textButton;
    }

    public void setTextButton(String textButton) {
        this.textButton = textButton;
    }
}