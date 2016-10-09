package com.anshumantripathi.mathquizapp;

import android.os.Bundle;

/**
 * Created by AnshumanTripathi on 10/8/16.
 */

public class RetainedFragment extends android.support.v4.app.Fragment {

    QuizContext data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(QuizContext instance){
        this.data = instance;
    }

    public QuizContext getData(){
        return data;
    }
}
