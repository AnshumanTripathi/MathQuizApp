package com.anshumantripathi.mathquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //View Elements
        TextView result = (TextView) findViewById(R.id.resultValue);
        Button playAgainButton = (Button) findViewById(R.id.playAgain);

        result.setText(String.valueOf(QuizContext.getInstance().getPoints()));

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reset Context
                QuizContext.getInstance().setPoints(0);
                QuizContext.getInstance().setNumberOfQuestions(3);
                
                //Start Quiz Again
                Intent intent = new Intent(ResultActivity.this,QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
