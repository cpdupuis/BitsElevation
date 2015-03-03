package com.bitsforabetterworld.bitselevation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements ElevationCallback {
    private LocationModel locationModel;
    private String units = "meters";
    private double unitMultiplier = 1.0;

    private double computeUnitMultiplier(String units) {
        if ("feet".equals(units)) {
            return 3.28084;
        }
        else {
            return 1.0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        setContentView(R.layout.activity_main);
        this.locationModel = new LocationModel(getApplicationContext(), this);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateElevation();
            }
        });

    }

    private void updateElevation() {
        locationModel.updateLocation();
    }

    private void updateUnits() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.units = sharedPreferences.getString("pref_units", "meters");
        this.unitMultiplier = computeUnitMultiplier(this.units);
        Toast toast = Toast.makeText(getApplicationContext(), "Units: "+this.units+"; mutiplier: "+this.unitMultiplier, Toast.LENGTH_LONG);
        toast.show();
    }

    public void elevationUpdated(double elevation, double accuracy) {
        double elevationInUnits = elevation * this.unitMultiplier;
        double accuracyInUnits = accuracy * this.unitMultiplier;
        TextView textView = (TextView) findViewById(R.id.elevationDisplay);
        textView.setText(""+elevationInUnits);

        textView = (TextView) findViewById(R.id.accuracyDisplay);
        textView.setText(""+accuracyInUnits);

        textView= (TextView) findViewById(R.id.elevationUnitsDisplay);
        textView.setText(this.units);

        textView = (TextView) findViewById(R.id.accuracyUnitsDisplay);
        textView.setText(this.units);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUnits();
        //updateElevation();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

