package com.bitsforabetterworld.bitselevation;

import java.util.Locale;

public class FormatUtils {

    static String formatLatLon(double latLon, char positiveDirection, char negativeDirection)
    {
        boolean isPositive = (latLon >= 0.0);
        double absLatLon = Math.abs(latLon);
        if (absLatLon > 180.0) {
            return "?";
        }
        double degrees = Math.floor(absLatLon);
        double degreeRemainder = absLatLon - degrees;
        double minutesAndSeconds = 60.0 * degreeRemainder;
        double minutes = Math.floor(minutesAndSeconds);
        double minuteRemainder = minutesAndSeconds - minutes;
        double seconds = 60.0 * minuteRemainder;

        return String.format(Locale.US, "%.0f\u00b0 %.0f\' %.3f\" %c", degrees, minutes, seconds, isPositive?positiveDirection:negativeDirection);

    }

    public static String formatLatitude(double latitude)
    {
        return formatLatLon(latitude, 'N', 'S');
    }

    public static String formatLongitude(double longitude)
    {
        return formatLatLon(longitude, 'E', 'W');
    }
}
