package com.example.jain.locationapiexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button locButton;
    TextView displayLoc;
    GPSTracker gpsT;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            if(ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

        locButton = (Button) findViewById(R.id.getlocation);
        displayLoc = (TextView) findViewById(R.id.locationview);

        locButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        gpsT = new GPSTracker(this);

        double latitude = gpsT.getLatitude();
        double longitude = gpsT.getLongitude();

        displayLoc.setText("Location is: " + latitude + " and " + longitude);
    }


}
