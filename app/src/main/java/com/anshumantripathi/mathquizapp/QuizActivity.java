package com.anshumantripathi.mathquizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    TextView operationText;
    TextView answerText;
    Button submitButton;
    Button clearButton;
    ImageButton homeButton;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //Layout Elements
        final TextView questionNumber = (TextView) findViewById(R.id.qNumber);
        operationText = (TextView) findViewById(R.id.operation);
        final TextView num1Text = (TextView) findViewById(R.id.numText1);
        final TextView num2Text = (TextView) findViewById(R.id.numText2);
        final TextView timer = (TextView) findViewById(R.id.timer);
        answerText = (TextView) findViewById(R.id.answer);
        submitButton = (Button) findViewById(R.id.enter);
        clearButton = (Button) findViewById(R.id.clear);
        homeButton = (ImageButton) findViewById(R.id.home);

        //Get operation from launcher activity
        Bundle extras = getIntent().getExtras();
        final String operation = extras.getString("operation");
        QuizContext.getInstance().setOperation(operation);

        Random randomNumberGenerator = new Random();
        //Generate Random Numbers
        int num1 = randomNumberGenerator.nextInt((9) + 1) + 1;
        QuizContext.getInstance().setNum1(num1);


        if (operation.equals("sub")) {
            //For subtraction generate number smaller than num1
            int num2 = randomNumberGenerator.nextInt((num1 - 1) + 1) + 1;
            QuizContext.getInstance().setNum2(num2);
        } else {
            int num2 = randomNumberGenerator.nextInt((9) + 1) + 1;
            QuizContext.getInstance().setNum2(num2);
        }


        //Count Down Timer for 5 seconds
        cdt = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int timeLeft = (int)millisUntilFinished/1000;
                timer.setText(String.valueOf(String.valueOf(timeLeft)));
                switch (QuizContext.getInstance().getOperation()){
                    case "add": operationText.setText("+");
                        break;
                    case "sub": operationText.setText("-");
                        break;
                    case "mul": operationText.setText("x");
                        break;
                }
                questionNumber.setText("#" + QuizContext.getInstance().getNumberOfQuestions());
                num1Text.setText(String.valueOf(QuizContext.getInstance().getNum1()));
                num2Text.setText(String.valueOf(QuizContext.getInstance().getNum2()));
            }

            @Override
            public void onFinish() {
                nextQuestion();
            }
        }.start();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerText.getText().toString();
                if(answer.equals("")){
                    generateSmallToast("Empty!");
                }else {
                    QuizContext.getInstance().setAnswer(Integer.parseInt(answer));
                    int solution = 0;
                    String operation = QuizContext.getInstance().getOperation();
                    switch (operation) {
                        case "add":
                            solution = QuizContext.getInstance().getNum1() + QuizContext.getInstance().getNum2();
                            break;
                        case "mul":
                            solution = QuizContext.getInstance().getNum1() * QuizContext.getInstance().getNum2();
                            break;
                        case "sub":
                            solution = QuizContext.getInstance().getNum1() - QuizContext.getInstance().getNum2();
                            break;
                    }

                    if (solution == QuizContext.getInstance().getAnswer()) {
                        int points = QuizContext.getInstance().getPoints();
                        QuizContext.getInstance().setPoints(++points);
                        generateSmallToast("Correct!");
                        nextQuestion();
                    } else {
                        generateSmallToast("Wrong!");
                        nextQuestion();
                    }
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.setText("");
            }
        });


//        Ancestral Navigation by clicking home button.
//        A Dialog is generated on clicking home button, confirming to exit
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
                alertDialogBuilder.setMessage("Exit Quiz?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cdt.cancel();
                        Intent intent = new Intent(QuizActivity.this, LauncherActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    //Generate a Dialog on back key pressed
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
        alertDialogBuilder.setMessage("Exit Quiz?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cdt.cancel();
                Intent intent = new Intent(QuizActivity.this, LauncherActivity.class);
                startActivity(intent);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //Set Answer Text
    public void setValue(View v) {
        String answer = answerText.getText().toString();
        if (answer.length() <= 2)
            answerText.append(v.getTag().toString());
    }

    //Go to next question
    public void nextQuestion() {
        cdt.cancel(); //Cancel current timer and move to next question
        QuizContext.getInstance().setNumberOfQuestions(QuizContext.getInstance().getNumberOfQuestions() + 1);
        if (QuizContext.getInstance().getNumberOfQuestions() <= 10) {
            //Still Questions left, Reload this activity
            finish();
            startActivity(getIntent());
        } else {
            //Al questions finished, Go to Result Activity
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuizActivity.this);
            alertDialogBuilder.setMessage("Your Score: " + QuizContext.getInstance().getPoints() + "\n" + "Play Again?");
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    QuizContext.getInstance().resetContext();
                    Intent intent = new Intent(QuizActivity.this, LauncherActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }


    //Generate a Toast for half millisecond
    public void generateSmallToast(String info) {
        final Toast toast = Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT);
        toast.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 500);
    }

}

