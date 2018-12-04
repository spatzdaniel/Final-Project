package com.example.spatz.madlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public madLibInfo lib;
    public int count;
    public String[] userResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        lib = new madLibInfo();
        userResponse = new String[lib.inputsNeeded.length];
        count = 0;
    }
    /**
     * The function that runs upon pressing the start button.
     * @param view
     */
    public void libTime(View view) {
        if (count < lib.inputsNeeded.length) {
            setContentView(R.layout.inputforthelib);
            TextView e = findViewById(R.id.inputneeded);
            e.setText(lib.inputsNeeded[count]);
        } else {
            beDone(view);
        }
    }
    /**
     * This is executed upon receiving all user inputs.
     * @param view
     */
    public void nextInput(View view) {
        userResponse[count] = "boat";
        count++;
        libTime(view);
    }
    public void beDone(View view) {
        setContentView(R.layout.finalmadlibview);
    }

}
