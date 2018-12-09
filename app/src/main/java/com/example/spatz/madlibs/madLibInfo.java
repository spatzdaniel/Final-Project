package com.example.spatz.madlibs;

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
    public String[] inputsNeeded;
    /**
     * This is the shell of the mad lib. It will be a string array of the original json object,
     * parsed around where the inputs need to be inserted.
     */
    public String[] madLibBeforeEntries;
    public RequestQueue queue;

    /**
     * The constructor for this has to take no inputs, run the random quote API, put the random
     * quote through the madlib API, and use the output from that to define the two variables
     * defined above.
     */
    public madLibInfo(RequestQueue newQueue) {
        queue = newQueue;
        API info = new API(queue);
        inputsNeeded = info.emptyLib.split("<|//>");
        madLibBeforeEntries = info.emptyLib.split(">|//<");
    }
}
