package com.bit.quizbot;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Start(View v){
        Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivityForResult(intent, 0);
    }
    public void trialchecker(View v){

        try {
            Thread.sleep(3000);
            if(isScreenOn(this)==false) {
                Intent intent = new Intent(MainActivity.this, turnonactivity.class);
                startActivity(intent);
                finish();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(":::","do not try");
        }
    }

    public boolean isScreenOn(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
            boolean screenOn = false;
            for (Display display : dm.getDisplays()) {
                if (display.getState() != Display.STATE_OFF) {
                    screenOn = true;
                }
            }
            return screenOn;
        } else {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            //noinspection deprecation
            return pm.isScreenOn();
        }
    }
}
