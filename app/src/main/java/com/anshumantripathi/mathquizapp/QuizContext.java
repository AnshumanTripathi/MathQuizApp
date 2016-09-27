package com.anshumantripathi.mathquizapp;

import java.util.Random;

/**
 * Created by AnshumanTripathi on 9/26/16.
 */

public class QuizContext {

    private static int answer;
    private static int num1;
    private static int num2;
    private static int points;
    private static QuizContext instance = null;
    private static int numberOfQuestions = 10;

    String[] operations = {"add","sub","mul"};

    private QuizContext(){}

    static QuizContext getInstance(){
        if(instance == null) {
            return new QuizContext();
        }
        return instance;
    }

    int getAnswer(){
        return answer;
    }
    void setAnswer(int answerUser) {
        answer = answerUser;
    }
    int getPoints() {
        return points;
    }

    void setPoints(int pointsUser) {
        points = pointsUser;
    }

    int getNumberOfQuestions(){
        return numberOfQuestions;
    }

    void setNumberOfQuestions(int questions){
        numberOfQuestions = questions;
    }

    String getOperation(){
        int index = new Random().nextInt(operations.length);
        return operations[index];
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int randomNum1) {
        num1 = randomNum1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int randomNum2) {
        num2 = randomNum2;
    }
}