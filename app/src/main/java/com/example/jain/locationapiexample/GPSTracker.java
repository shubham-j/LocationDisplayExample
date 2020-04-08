package com.example.jain.locationapiexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by shubham on 4/7/20.
 */

public class GPSTracker extends Service implements LocationListener{

    private final Context mContext;
    LocationManager lManager;
    boolean isGPSenabled = false;
    Location location;
    double latitude;
    double longitude;

    public GPSTracker(Context context){
        this.mContext = context;
        getLocation();
    }

    public Location getLocation(){
        try{
            lManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            isGPSenabled = lManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if(isGPSenabled){
                lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);

                if(lManager != null){
                    location = lManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }

    public double getLatitude(){
        if (location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude(){
        if (location!=null){
            longitude = location.getLongitude();
        }
        return longitude;
    }


    @Override
    public void onLocationChanged(Location location){

    }

    @Override
    public void onProviderDisabled(String provider){}

    @Override
    public void onProviderEnabled(String provider){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}

    @Override
    public IBinder onBind(Intent i){return null;}
}
