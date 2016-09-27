package com.anshumantripathi.mathquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        //Layout Elements
        TextView questionsText = (TextView) findViewById(R.id.questions);
        final TextView answerText = (TextView) findViewById(R.id.answer);
        final SeekBar answerBar = (SeekBar) findViewById(R.id.seekBar);
        Button submitButton = (Button) findViewById(R.id.submit);

        final String operation = QuizContext.getInstance().getOperation();
        Random randomNumberGenerator = new Random();

        //Generate Random Numbers
        int num1 = randomNumberGenerator.nextInt((9) + 1) + 1;
        QuizContext.getInstance().setNum1(num1);
        if(operation.equals("sub")) {
            int num2 = randomNumberGenerator.nextInt((num1) + 1) + 1;
            QuizContext.getInstance().setNum2(num2);
        }else{
            int num2 = randomNumberGenerator.nextInt((9) + 1) + 1;
            QuizContext.getInstance().setNum2(num2);
        }

        if(operation.equals("add")) {
            questionsText.setText(QuizContext.getInstance().getNum1() + " + " + QuizContext.getInstance().getNum2());
            answerBar.setMax(30);
        }
        else if(operation.equals("mul")) {
            questionsText.setText(QuizContext.getInstance().getNum1() + " X  " + QuizContext.getInstance().getNum2());
            answerBar.setMax(100);
        }
        else if(operation.equals("sub")) {
            questionsText.setText(QuizContext.getInstance().getNum1() + " - " + QuizContext.getInstance().getNum2());
            answerBar.setMax(30);
        }

        answerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Change text view according to SeekBar
                answerText.setText(String.valueOf(progress));
                QuizContext.getInstance().setAnswer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Get the final value
                QuizContext.getInstance().setAnswer(seekBar.getProgress());
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Button Clicked!", Toast.LENGTH_SHORT).show();
                int solution = 0;

                if(operation.equals("add"))
                    solution = QuizContext.getInstance().getNum1() + QuizContext.getInstance().getNum2();
                else if(operation.equals("mul"))
                    solution = QuizContext.getInstance().getNum1() * QuizContext.getInstance().getNum2();
                else if(operation.equals("sub"))
                    solution = QuizContext.getInstance().getNum1() - QuizContext.getInstance().getNum2();

                if (solution == QuizContext.getInstance().getAnswer()) {
                    int points = QuizContext.getInstance().getPoints();
                    QuizContext.getInstance().setPoints(++points);
                    Toast.makeText(getBaseContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                    QuizContext.getInstance().setNumberOfQuestions(QuizContext.getInstance().getNumberOfQuestions() - 1);
                    if(QuizContext.getInstance().getNumberOfQuestions() > 0 ) {
                        //Still Questions left, Reload this activity
                        finish();
                        startActivity(getIntent());
                    }else{
                        //Al questions finished, Go to Result Activity
                        Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                    QuizContext.getInstance().setNumberOfQuestions(QuizContext.getInstance().getNumberOfQuestions() - 1);
                    if(QuizContext.getInstance().getNumberOfQuestions() > 0 ) {
                        //Still Questions left, Reload this activity
                        finish();
                        startActivity(getIntent());
                    }else{
                        //Al questions finished, Go to Result Activity
                        Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
