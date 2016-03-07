package com.startemplan.vyuspot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


public class SplashScreen extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        goCheck();


    }

    public void goCheck() {

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
        else {





            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                    String code = sharedPreferences.getString("test", "");

                    Log.d("tag", "<-----CODE----->" + code);
                    if ((code == "")) {
                        Intent i = new Intent(SplashScreen.this, Register.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SplashScreen.this, Dashboard.class);
                        startActivity(i);
                    }

                    finish();
                }
            }, 3000);





        /*    Thread goDash = new Thread() {

                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {


                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                        String code= sharedPreferences.getString("test", "");

                        if(code == "")
                        {
                            Intent goDashboard = new Intent(getApplicationContext(), Register.class);
                            startActivity(goDashboard);
                        }

                        else {
                            Intent goDashboard = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(goDashboard);
                        }

                    }
                }
            };
            goDash.start();*/
        }
    }


    public void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }





}
