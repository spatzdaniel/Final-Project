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
    public madLibInfo lib;
    public int count;
    public String[] userResponse;
    public static API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        queue = Volley.newRequestQueue(this);
        api = new API(queue);
        lib = new madLibInfo(api);
        userResponse = new String[lib.inputsNeeded.length];
        count = 0;
    }
    /**
     * The function that runs upon pressing the start button.
     * Also runs each time you click the next button.
     * Displays the Input needed at the top of the screen
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
     * This functions runs upon clicking the next button.
     */
    public void next(View view) {
        EditText input = findViewById(R.id.userInput);
        userResponse[count] = input.getText().toString();
        Log.d("test", userResponse[count]);
        count++;
        libTime(view);
    }
    /**
     * This is executed upon receiving all user inputs.
     * @param view
     */
    public void beDone(View view) {
        setContentView(R.layout.finalmadlibview);
        TextView finishedLib = findViewById(R.id.finishedLib);
        String combine = "";
        for (int i = 0; i < userResponse.length; i++) {
            combine += lib.madLibBeforeEntries[i] + userResponse[i];
        }
        combine += lib.madLibBeforeEntries[count];
        finishedLib.setText(combine);
        finishedLib.setText(api.quote);
    }
    /**
     * This runs when the button is clicked on the last page.
     * Restarts the app.
     */
    public void restart(View view) {
        setContentView(R.layout.home);
        lib = new madLibInfo(api);
        userResponse = new String[lib.inputsNeeded.length];
        count = 0;
    }
}
