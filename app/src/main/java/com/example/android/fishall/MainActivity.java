package com.example.android.fishall;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private TextView mWeather;

    private TextView mHumidity;

    private TextView mWind;

    private TextView mPressure;

    private TextView mCelcius;

    private TextView mLocation;

    private ProgressBar mLoadingIndicator;

    private TextView mErrorMessageDisplay;

    private String location;

    ImageView weatherIcon;

    private BroadcastReceiver broadcastReceiver;

    private final int LOCATION_PERMISSION_CODE = 11;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        location = "istanbul";

        weatherIcon = (ImageView) findViewById(R.id.weather_img);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);

        mHumidity = (TextView) findViewById(R.id.humidity);

        mPressure = (TextView) findViewById(R.id.pressure);

        mLocation = (TextView) findViewById(R.id.location);

        mWeather = (TextView) findViewById(R.id.weather_description);

        mWind = (TextView) findViewById(R.id.wind);

        mCelcius = (TextView) findViewById(R.id.weather_forecast_results_json);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);


        runtimePermissions();

        getWeatherAPI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * Location service operations are done here
         */
        getLocationInfo();

        if(broadcastReceiver==null)
        {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                     location = intent.getExtras().getString("coordinates");
                }
            };
        }

        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null)
        {
            unregisterReceiver(broadcastReceiver);
        }
    }

    private boolean runtimePermissions()
    {
        /**
         * In this function, all necessary permissions are asked from the user
         */
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_PERMISSION_CODE);

            return true;
        }

        return false;
    }

    public void getLocationInfo()
    {
        Intent locationIntent = new Intent(getApplicationContext(),GPS_Service.class);
        startService(locationIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_PERMISSION_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                //TODO Handle permission granted
            }
            else {
                //TODO handle permission denied
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void getWeatherAPI()
    {
        URL weatherSearchURL = NetworkUtils.buildUrl(location);
        new WeatherForecastTask().execute(weatherSearchURL);
    }



    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mCelcius.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        // First, hide the currently visible data
        mCelcius.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class WeatherForecastTask extends AsyncTask<URL, Void, String>
    {
        @Override
        protected void onPreExecute() {
            /**
             * This method is in Async task,
             * on first execution of async task this method creates a loading screen
             */
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            /**
             * After onPreExecute finishes execution, doInBackground
             * gets information from NetworkUtils class and sends weatherForecastResults
             * to onPostExecute
             */
            URL searchUrl = urls[0];
            String weatherForecastResults = null;
            try {
                weatherForecastResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return weatherForecastResults;
        }

        @Override
        protected void onPostExecute(String weatherForecastResults) {
            /**
             * onPostExecute is the final phase of an Async task,
             * onPostExecute removes the loading bar and constructs the weather
             * results page using JSON Parsing methods
             */
            super.onPostExecute(weatherForecastResults);
            mLoadingIndicator.setVisibility(View.INVISIBLE);


            if (weatherForecastResults != null && !weatherForecastResults.equals("")) {
                // Call showJsonDataView if we have valid, non-null results
                showJsonDataView();


                String JSONString = weatherForecastResults;

                String celcius = getCelciusJSON(weatherForecastResults);
                String wind = getWindJSON(weatherForecastResults);
                String humidity = getHumidityJSON(weatherForecastResults);
                String pressure = getPressureJSON(weatherForecastResults);
                String location = getFullLocationJSON(weatherForecastResults);
                String weather = getWeatherJSON(weatherForecastResults);

                if(weather.compareTo("Partly Cloudy") == 0)
                    weatherIcon.setImageResource(R.drawable.ic_cloudy);
                if(weather.compareTo("Mostly Cloudy") == 0)
                    weatherIcon.setImageResource(R.drawable.ic_cloudy);
                if(weather.compareTo("Clear") == 0)
                    weatherIcon.setImageResource(R.drawable.ic_clear);
                if(weather.compareTo("Fog") == 0)
                    weatherIcon.setImageResource(R.drawable.ic_fog);
                if(weather.compareTo("Snow") == 0)
                    weatherIcon.setImageResource(R.drawable.ic_snow);
                if(weather.compareTo("Rainy")==0)
                    weatherIcon.setImageResource(R.drawable.ic_rain);
                if(weather.compareTo("Scattered Clouds") == 0 || weather.compareTo("Overcast") == 0)
                    weatherIcon.setImageResource(R.drawable.ic_cloudy);
                if(weather.contains("Storm") || weather.contains("Thunder"))
                    weatherIcon.setImageResource(R.drawable.ic_storm);

                mCelcius.setText(celcius);
                mWind.setText(wind);
                mHumidity.setText(humidity);
                mPressure.setText(pressure);
                mLocation.setText(location);
                mWeather.setText(weather);

            }
            else {
                // Call showErrorMessage if the result is null in onPostExecute
                showErrorMessage();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            onResume();
            getWeatherAPI();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getFullLocationJSON(String JSONString)
    {
        String location = null;
        try
        {
            JSONObject forecast = new JSONObject(JSONString);
            JSONObject weather = forecast.getJSONObject("current_observation");
            JSONObject full = weather.getJSONObject("display_location");
            location = full.getString("full");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            showErrorMessage();
        }

        return location;
    }

    public String getWeatherJSON(String JSONString)
    {
        String weatherCond = null;

        try
        {
            JSONObject forecast = new JSONObject(JSONString);
            JSONObject weather = forecast.getJSONObject("current_observation");
            weatherCond = weather.getString("weather");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            showErrorMessage();
        }

        return weatherCond;
    }
    public String getCelciusJSON(String JSONString)
    {
        String degree = null;
        try
        {
            JSONObject forecast = new JSONObject(JSONString);
            JSONObject weather = forecast.getJSONObject("current_observation");
            degree = weather.getString("temp_c");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            showErrorMessage();
        }
        return degree;
    }
    public String getHumidityJSON(String JSONString)
    {
        String humidity = null;
        try
        {
            JSONObject forecast = new JSONObject(JSONString);
            JSONObject weather = forecast.getJSONObject("current_observation");
            humidity =  weather.getString("relative_humidity");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            showErrorMessage();
        }
        return humidity;
    }

    public String getWindJSON(String JSONString)
    {
        String wind = null;
        String direction = null;
        try
        {
            JSONObject forecast = new JSONObject(JSONString);
            JSONObject weather = forecast.getJSONObject("current_observation");
            wind = weather.getString("wind_kph");
            direction = weather.getString("wind_dir");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            showErrorMessage();
        }
        wind = direction + " - " + wind + "km/s";
        return wind;
    }

    public String getPressureJSON(String JSONString)
    {
        String pressure = null;
        try{
            JSONObject forecast = new JSONObject(JSONString);
            JSONObject weather = forecast.getJSONObject("current_observation");
            pressure = weather.getString("pressure_mb");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            showErrorMessage();
        }
        return pressure;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fish_encyclopedia) {
            Context context = MainActivity.this;
            Class destinationClass = FishEncyclopedia.class;
            Intent intentToStartEncyclopediaActivity = new Intent((Context) context, destinationClass);
            startActivity(intentToStartEncyclopediaActivity);
        } /*
        else if (id == R.id.nav_settings) {
            Context context = MainActivity.this;
            Class destinationClass = SettingsActivity.class;
            Intent settingsIntent = new Intent((Context) context, destinationClass);
            startActivity(settingsIntent);

        }*/ else if (id == R.id.nav_about_us) {

            Context context = MainActivity.this;
            Class aboutClass = AboutActivitiy.class;
            Intent intentToStartAbout = new Intent((Context) context, aboutClass);

            startActivity(intentToStartAbout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
