package com.example.spatz.madlibs;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONArray;
import org.unbescape.html.HtmlEscape;

public class API {
    private final static String quoteURL = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1\n";
    private final static String libURL = "http://libberfy.herokuapp.com?/?blanks=1&q=";
    private final static String TAG = "API Manager";
    private static RequestQueue queue;
    public String quote = "";
    private String author = "";
    public String emptyLib = "";
    API(final RequestQueue rQueue) {
        queue = rQueue;
        generate();
        libGenerate(quote);
    }
    private void generate() {
        Log.d(TAG, "Generating quote...");
        try {
            JsonArrayRequest jRequest = new JsonArrayRequest(Request.Method.GET, quoteURL, null, new Response.Listener<JSONArray>() {
                @Override
                public  void onResponse(final JSONArray response) {
                    //Log.d(TAG, response.toString());
                    try {
                        JSONObject json = response.getJSONObject(0);
                        quote = HtmlEscape.unescapeHtml(json.getString("content"));
                        author = json.getString("title");
                        Log.d(TAG, quote);

                        //Section to format quote properly...
                        String[] array = quote.split("<p>");
                        quote = array[1];
                        array = quote.split("</p>");
                        quote = array[0];
                        //

                        libGenerate(quote);
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
    private void libGenerate(final String quote) {
        Log.d(TAG,"Generating lib...");
        try {
            JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, libURL+quote, null, new Response.Listener<JSONObject>() {
                @Override
                public  void onResponse(final JSONObject response) {
                    Log.d(TAG, response.toString());
                    try {
                        emptyLib = response.getString("madlib");
                        Log.d(TAG, emptyLib);
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
