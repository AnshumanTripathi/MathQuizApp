package com.anshumantripathi.mathquizapp;

import java.util.Random;

/**
 * Created by AnshumanTripathi on 9/26/16.
 * <p>
 * Context Class used to maintain state of the quiz.
 */

public class QuizContext {

    private static QuizContext instance = null;

    private static int answer;
    private static int num1;
    private static int num2;
    private static int points;
    private static int numberOfQuestions = 1;

    private static String operation;

    private QuizContext() {
    }

    static QuizContext getInstance() {
        if (instance == null) {
            return new QuizContext();
        }
        return instance;
    }

    int getAnswer() {
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

    int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    void setNumberOfQuestions(int questions) {
        numberOfQuestions = questions;
    }

    String getOperation() {
        return this.operation;
    }


    public void setOperation(String operationUser) {
        this.operation = operationUser;
        System.out.print("Operation set: "+this.operation);
    }

    int getNum1() {
        return num1;
    }

    void setNum1(int randomNum1) {
        num1 = randomNum1;
    }

    int getNum2() {
        return num2;
    }

    void setNum2(int randomNum2) {
        num2 = randomNum2;
    }

    //Reset QuizContext
    void resetContext() {
        QuizContext.getInstance().setPoints(0);
        QuizContext.getInstance().setNumberOfQuestions(1);
    }
}