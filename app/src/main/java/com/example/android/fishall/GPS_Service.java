package com.example.android.fishall;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ALLDe on 15-May-18.
 */

public class GPS_Service extends Service {


    private LocationListener listener;
    private LocationManager manager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent mainIntent = new Intent("location_update");
                mainIntent.putExtra("coordinates", location.getLatitude() + "," + location.getLongitude());

                sendBroadcast(mainIntent);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                //Left as blank since it's no use for the program
            }

            @Override
            public void onProviderEnabled(String s) {
                //Left as blank since it's no use for the program
            }

            @Override
            public void onProviderDisabled(String s) {
                //Left as blank since it's no use for the program
            }
        };
        manager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(manager != null)
        {
            manager.removeUpdates(listener);
        }
    }
}
