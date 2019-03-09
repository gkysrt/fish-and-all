package com.example.android.fishall;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ALLDe on 14-May-18.
 */

public class NetworkUtils {

    private static String WEATHER_BASE_URL = "http://api.wunderground.com/api/c40f8139eea4abfb/conditions/forecast/alert";
    private static String PARAM_QUERY = "q";


    /**
     * Builds the URL used to query Github.
     *
     * @param weatherForecastQuery The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String weatherForecastQuery) {

        weatherForecastQuery = weatherForecastQuery.concat(".json");

        Uri builtUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendPath(PARAM_QUERY)
                .appendPath(weatherForecastQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
