package mariuszbaleczny.compass;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {

    public static boolean isCoordinateInRange(double coordinate, boolean latitude) {
        if (latitude) {
            return (coordinate >= Constants.LATITUDE_MIN && coordinate <= Constants.LATITUDE_MAX);
        } else {
            return (coordinate >= Constants.LONGITUDE_MIN && coordinate <= Constants.LONGITUDE_MAX);
        }
    }

    public static int convertRadiansToDegreesRounded(float angleInRadians) {
        return Math.round((int) ((Math.toDegrees(angleInRadians) + Constants.FULL_ANGLE) % Constants.FULL_ANGLE));
    }

    static boolean isCompassSensorPresent(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS);
    }

    /**
     * Source of location are GPS and NETWORK providers as it provides high accuracy
     */
    static boolean isLocationServicesEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            return (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    && lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
        } catch (Exception e) {
            Log.e(context.getClass().getName(), e.getMessage());
            return false;
        }
    }

    public static void hideKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
}
