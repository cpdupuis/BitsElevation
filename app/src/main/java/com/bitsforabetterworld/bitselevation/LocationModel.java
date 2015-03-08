package com.bitsforabetterworld.bitselevation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

public class LocationModel implements LocationListener {
    private final LocationManager locationManager;
    private final ElevationCallback elevationCallback;
    private final Criteria criteria;

    public LocationModel(Context context, ElevationCallback elevationCallback) {
        this.elevationCallback = elevationCallback;
        this.criteria = new Criteria();
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void updateLocation() {
        String locationProviderName = getBestLocationProviderName();
        if (locationProviderName != null) {
            locationManager.requestSingleUpdate(locationProviderName, this, Looper.getMainLooper());
        }
    }

    private String getBestLocationProviderName() {
        if (locationManager != null) {
            // Let's get a good location provider.
            return locationManager.getBestProvider(criteria, true /*enabledOnly*/);
        }
        return null;
    }

    public Location getLastLocation() {
        if (locationManager != null) {
            return locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true /*enabledOnly*/));
        }
        else {
            return null;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            elevationCallback.elevationUpdated(location.getAltitude(), location.getAccuracy());
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
