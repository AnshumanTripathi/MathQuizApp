package com.anshumantripathi.mathquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.anshumantripathi.mathquizapp.R.string.result;

public class ResultActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static String MyPreferences = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Reset shared preferences when Quiz ends
        sharedPreferences = getSharedPreferences(MyPreferences,0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.commit();


        //View Elements
        TextView result = (TextView) findViewById(R.id.resultValue);
        Button playAgainButton = (Button) findViewById(R.id.playAgain);
        Button goHomeButton = (Button) findViewById(R.id.goHome);

        result.setText(String.valueOf(QuizContext.getInstance().getPoints()));

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizContext.getInstance().resetContext();
                //Start Quiz Again
                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizContext.getInstance().resetContext();
                //Start Quiz Again
                Intent intent = new Intent(ResultActivity.this, LauncherActivity.class);
                startActivity(intent);
            }
        });

    }
}
