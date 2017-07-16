package com.example.android.redroom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.List;

import static android.R.attr.author;
import static android.R.attr.rating;

/**
 * Created by victo on 7/4/2017.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static ArrayList<Book> extractVolumes(String requestUrl) {
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
                Log.e(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
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
            while (line != null) {
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
            JSONArray jsonItem = jsonBook.getJSONArray("items");

            for (int i = 0; i < jsonItem.length(); i++) {
                JSONObject jsonCurrent = jsonItem.getJSONObject(i);
                JSONObject jsonVolumeInfo = jsonCurrent.getJSONObject("volumeInfo");

                String title;
                Bitmap bmp;
                String author;
                if (jsonVolumeInfo.has("title") && jsonVolumeInfo.has("authors") && jsonVolumeInfo.has("imageLinks")) {

                    //Get title
                    title = jsonVolumeInfo.getString("title");

                    //Get author
                    StringBuilder authors = new StringBuilder();
                    JSONArray jsonAuthor = jsonVolumeInfo.getJSONArray("authors");
                    for (int j = 0; j < jsonAuthor.length(); j++) {
                        String authorLine = jsonAuthor.getString(j);
                        if (j != 0) {
                            authors.append(", ");
                        }
                        authors.append(authorLine);
                    }
                    author = authors.toString();

                    //Get image
                    JSONObject jsonImageLinks = jsonVolumeInfo.getJSONObject("imageLinks");
                    String image = jsonImageLinks.getString("smallThumbnail");
                    URL imageUrl = getUrl(image);
                    bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                } else {
                    continue;
                }

                //Get previewLink
                String previewLink = null;
                if (jsonVolumeInfo.has("previewLink")) {
                    previewLink = jsonVolumeInfo.getString("previewLink");
                }

                //Get webReaderLink
                String webReaderLink = null;
                if (jsonVolumeInfo.has("accessInfo")) {
                    JSONObject jsonAccessInfo = jsonCurrent.getJSONObject("accessInfo");
                    if (jsonAccessInfo.has("webReaderLink")) {
                        webReaderLink = jsonAccessInfo.getString("webReaderLink");
                    }
                }

                //Get textSnippet
                String textSnippet = null;
                if (jsonCurrent.has("searchInfo")) {
                    JSONObject jsonSearchInfo = jsonCurrent.getJSONObject("searchInfo");
                    if (jsonSearchInfo.has("textSnippet")) {
                        textSnippet = jsonSearchInfo.getString("textSnippet");
                    }
                }

                //Get description
                String description = null;
                if (jsonVolumeInfo.has("description")) {
                    description = jsonVolumeInfo.getString("description");
                }

                //get ratingAverage
                String ratingAverage = null;
                if (jsonVolumeInfo.has("averageRating")) {
                    ratingAverage = jsonVolumeInfo.getString("averageRating");
                }

                //get ratingCount
                String ratingCount = null;
                if (jsonVolumeInfo.has("ratingsCount")) {
                    ratingCount = jsonVolumeInfo.getString("ratingsCount");
                }

                books.add(new Book(title, author, bmp, previewLink, webReaderLink, textSnippet, description, ratingAverage, ratingCount));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSON results", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not retrieve JSON results", e);
        }
        return books;
    }
}
