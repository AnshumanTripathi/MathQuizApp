package com.anshumantripathi.mathquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Home Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addButton = (Button) findViewById(R.id.add);
        final Button subButton = (Button) findViewById(R.id.sub);
        final Button mulButton = (Button) findViewById(R.id.mul);
        final Button exitButton = (Button) findViewById(R.id.exit);

        //Start Addition Quiz
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, QuizActivity.class);
                intent.putExtra("operation","add");
                startActivity(intent);

            }
        });

        //Start Subtraction Quiz
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, QuizActivity.class);
                intent.putExtra("operation","sub");
                startActivity(intent);
            }
        });

        //Start Multiplication Quiz
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LauncherActivity.this, QuizActivity.class);
                intent.putExtra("operation","mul");
                startActivity(intent);
            }
        });

        //Exit button redirects to Android Home
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
            }
        });
    }
}