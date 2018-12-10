package com.example.spatz.madlibs;
import android.util.Log;
import java.util.List;
import java.util.ArrayList;

import com.android.volley.RequestQueue;

/**
 * This class sets up the madLib for use so that it can be interacted with by the rest of the app
 */
public class madLibInfo {
    /**
     * First thing to do is set up several variables which will be used by the app
     */
    /**
     * Will store a StringArray of all the inputs needed, in order, to complete the madlib.
     * Initially, all the entries will be stored as "noun", "verb" or some other type of word.
     */
    public List<String> inputsNeeded = new ArrayList<String>();
    /**
     * This is the shell of the mad lib. It will be a string array of the original json object,
     * parsed around where the inputs need to be inserted.
     */
    public List<String> madLibBeforeEntries = new ArrayList<String>();

    /**
     * Stores API info
     */
    public API info;

    /**
     * The constructor for this has to take no inputs, run the random quote API, put the random
     * quote through the madlib API, and use the output from that to define the two variables
     * defined above.
     */
    public madLibInfo(API api) {
        info = api;
        String[] split = info.emptyLib.split("<|//>");
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                if (i % 2 == 0) {
                    inputsNeeded.add(inputsNeeded.size(), split[i]);
                } else {
                    madLibBeforeEntries.add(madLibBeforeEntries.size(), split[i]);
                }
            }
        }
        Log.d("LibConstructor", info.emptyLib);
    }
}
