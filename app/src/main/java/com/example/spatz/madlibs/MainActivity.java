package com.example.spatz.madlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
    private String[] inputsNeeded = new String[]{"noun", "verb", "adjective"};

    public void libTime(View view) {

        setContentView(R.layout.inputforthelib);
    }

}
