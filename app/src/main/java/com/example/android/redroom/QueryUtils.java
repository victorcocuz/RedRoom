package com.example.android.redroom;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by victo on 7/4/2017.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static ArrayList<Book> extractVolumes(String requestUrl) {
//        ArrayList<Book> books = new ArrayList<>();
//        books.add(new Book("title 1", "author 1"));
//        books.add(new Book("title 2", "author 3"));
//        books.add(new Book("title 3", "author 3"));
//        books.add(new Book("title 4", "author 4"));
//        books.add(new Book("title 5", "author 5"));
//        books.add(new Book("title 6", "author 6"));

        URL url = getUrl(requestUrl);

    String jsonResponse = null;
        try {
        jsonResponse = makeHttpRequest(url);
    } catch (IOException e) {
        Log.e(LOG_TAG, "Error closing input stream", e);
    }
    ArrayList<Book> books = extractDataFromJSON(jsonResponse);
        return books;
    }

    public static URL getUrl(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Could not create url", e);
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.i(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not retrieve JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (reader != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Book> extractDataFromJSON(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();

        try {
            JSONObject jsonBook = new JSONObject(jsonResponse);
            JSONArray jsonItem = jsonBook.getJSONArray("Item");

            for (int i = 0; i < jsonItem.length(); i++) {
                JSONObject jsonCurrent = jsonItem.getJSONObject(i);
                JSONObject jsonVolumeInfo = jsonCurrent.getJSONObject("volumeInfo");

                String title = jsonVolumeInfo.getString("title");
                String subtitle = jsonVolumeInfo.getString("subtitle");

                books.add(new Book(title, subtitle));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSON results", e);
        }
        return books;
    }
}
