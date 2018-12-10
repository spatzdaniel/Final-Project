package com.example.spatz.madlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    public static RequestQueue queue;
    public int count;
    public static API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        queue = Volley.newRequestQueue(this);
        api = new API(queue);
        count = 0;
        Log.d("MAIN", Integer.toString(api.inputsNeeded.size()));
    }
    /**
     * The function that runs upon pressing the start button.
     * Also runs each time you click the next button.
     * Displays the Input needed at the top of the screen
     * @param view
     */
    public void libTime(View view) {
        Log.d("Size of Inputs Needed", api.emptyLib);
        if (count < api.inputsNeeded.size()) {
            setContentView(R.layout.inputforthelib);
            TextView e = findViewById(R.id.inputneeded);
            String[] split = api.inputsNeeded.get(count).split("_");
            String toReturn = "";
            for (int i = 0; i < split.length; i++) {
                toReturn += split[i] + " ";
            }
            e.setText(toReturn);
        } else {
            beDone(view);
        }
    }
    /**
     * This functions runs upon clicking the next button.
     */
    public void next(View view) {
        EditText input = findViewById(R.id.userInput);
        if (input.getText() == null || input.getText().length() == 0) {
            TextView e = findViewById(R.id.inputneeded);
            e.setText("Please input a(n) " + api.inputsNeeded.get(count));
        } else {
            api.userResponse[count] = input.getText().toString();
            Log.d("test", api.userResponse[count]);
            count++;
            libTime(view);
        }
    }
    /**
     * This is executed upon receiving all user inputs.
     * @param view
     */
    public void beDone(View view) {
        setContentView(R.layout.finalmadlibview);
        TextView finishedLib = findViewById(R.id.finishedLib);
        String combine = "";
        for (int i = 0; i < api.userResponse.length; i++) {
            combine += api.madLibBeforeEntries.get(i) + api.userResponse[i];
        }
        if (api.madLibBeforeEntries.size() > api.userResponse.length) {
            combine +=  api.madLibBeforeEntries.get(count);
        }
        finishedLib.setText(combine);
    }
    /**
     * This runs when the button is clicked on the last page.
     * Restarts the app.
     */
    public void restart(View view) {
        setContentView(R.layout.home);
        api = new API(queue);
        count = 0;
    }
}
