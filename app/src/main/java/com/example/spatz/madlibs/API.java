package com.example.spatz.madlibs;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONArray;

public class API {
    private final static String quoteURL = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1\n";
    private final static String TAG = "API Manager";
    private static RequestQueue queue;
    public String quote = null;
    public String author = null;
    public String emptyLib = null;
    API(final RequestQueue rQueue) {
        queue = rQueue;
    }
    private void generate() {
        Log.d("Tag", "Clicked!");
        try {
            JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.GET, quoteURL, null, new Response.Listener<JSONArray>() {
                @Override
                public  void onResponse(final JSONArray response) {
                    Log.d(TAG, response.toString());
                    try {
                        JSONObject json = response.getJSONObject(0);
                        quote = json.getString("content");
                        author = json.getString("title");
                        //Log.d(TAG, content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.toString());
                }
            });
            queue.add(jRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
