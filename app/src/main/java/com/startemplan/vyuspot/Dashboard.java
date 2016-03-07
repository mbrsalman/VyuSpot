package com.startemplan.vyuspot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by User on 21-12-2015.
 */
public class Dashboard extends Activity {

    ImageView a, b, c, d, e, sos, voic,contx;
    TextView t1, t2, t3, t4, t5,welcome;
    GpsT gps;
    String lati,longi,namecode;
    SharedPreferences tok;
    public Menu menu;
    MenuItem its,mi;

    SweetAlertDialog pDialog;


    String sois, sois1, sois2, sois3, sois4, sois5, sois6, sois7, sois8, sois9, sois10, sois11;



    public static String URL_SOS = "http://sqindia01.cloudapp.net/vyuspot/api/v1/spot/addSos";

    String token;


    @Override
    protected void onRestart() {
        super.onRestart();


        Log.d("tag",""+token);

      /*  if (token != ""+null) {
            its.setTitle("LogOut");
        } else {
            its.setTitle("Register");
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //tok = getSharedPreferences("token",Context.MODE_PRIVATE);

       // SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("pus","");
        editor.commit();
        token= sharedPreferences.getString("token", "");

        namecode = sharedPreferences.getString("name", "");

        Log.d("tag", "<-----name----->" + namecode);


        welcome = (TextView) findViewById(R.id.welcome);

        if ((namecode == "")) {
            welcome.setText("Welcome Guest");
        } else {
            welcome.setText("Welcome "+namecode);
        }








       /* t1 = (TextView) findViewById(R.id.tt);




        TelephonyManager phoneManager = (TelephonyManager)
                getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        /*//* String phoneNumber = phoneManager.getLine1Number();
        String ss = phoneManager.getNetworkOperator();

        sois = phoneManager.getDeviceId();
        sois1 = phoneManager.getLine1Number();
        sois2 = phoneManager.getDeviceSoftwareVersion();
        sois3= phoneManager.getNetworkCountryIso();
        sois4 = phoneManager.getNetworkOperator();
        sois5 = phoneManager.getNetworkOperatorName();
        sois6 = String.valueOf(phoneManager.getNetworkType());
        sois7 = String.valueOf(phoneManager.getSimState());
        sois8 = phoneManager.getSimCountryIso();
        sois9 = phoneManager.getSimOperator();


        CellLocation sois21 = phoneManager.getCellLocation();
        String sa = String.valueOf(sois21);
        sois10 = phoneManager.getSimOperatorName();
        sois11 = phoneManager.getVoiceMailNumber();

        List<CellInfo> so2 = phoneManager.getAllCellInfo();

        CellInfo asdf = so2.get(0);
//        CellInfo asdf1 = so2.get(1);
//        CellInfo asdf2 = so2.get(2);
       // CellInfo asdf3 = so2.get(3);
       // CellInfo asdf4 = so2.get(4);
       // CellInfo asdf5 = so2.get(5);



       // t1.setText(sa+"\n"+sois+sois1+sois10+sois11+sois2+sois3+sois4+sois5+sois6+sois7+sois8+sois9);

        t1.setText(sa+"\n"+asdf+"\n"+sois2+"\n\n"+sois1);

        Log.d("phone", sois);
        Log.d("phone",sois1);
        Log.d("phone",sois2);
        Log.d("phone",sois3);
        Log.d("phone",sois4);
        Log.d("phone",sois5);
        Log.d("phone",sois6);
        Log.d("phone",sois7);
        Log.d("phone",sois8);
        Log.d("phone",sois9);

        Log.d("phone",sois10);


//        Log.d("phone",sois11);

        Toast.makeText(getApplicationContext(),sois,Toast.LENGTH_LONG).show();
*/

       /* ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);*/


        a = (ImageView) findViewById(R.id.aa);
        b = (ImageView) findViewById(R.id.bb);
        c = (ImageView) findViewById(R.id.cc);
        d = (ImageView) findViewById(R.id.dd);
        e = (ImageView) findViewById(R.id.ee);
        sos = (ImageView) findViewById(R.id.sos);
        voic = (ImageView) findViewById(R.id.voic);
        contx = (ImageView) findViewById(R.id.context);
     /*   registerForContextMenu(contx);*/
        contx.setOnCreateContextMenuListener(this);

       /* contx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });*/
//        openContextMenu(contx);

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);
        t4 = (TextView) findViewById(R.id.text4);
        t5 = (TextView) findViewById(R.id.text5);


        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/robol.ttf");
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);
        t4.setTypeface(tf);
        t5.setTypeface(tf);


        //startService(new Intent(getApplicationContext(), TimeService.class));
       // Log.d("tag", "working");
        //token = tok.getString("token",""+null);
       // Log.d("tag", "" + token);

        //MenuItem itemi = menu.findItem(R.id.item1);

        //registerForContextMenu(contx);









        contx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openContextMenu(v);


   /*             final PopupMenu popup = new PopupMenu(Dashboard.this, contx);
                popup.getMenuInflater().inflate(R.menu.opt_menu, popup.getMenu());
*//*
                Menu mOptionsMenu = (Menu) popup;


                if (token.length() < 5) {
                    Log.d("tag", "<5");
                } else if (token.length() > 9) {
                    mOptionsMenu.getItem(R.id.item1).setTitle("Logout");
                    Log.d("tag", ">5");
                }*//*


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()                                                                           {
                    public boolean onMenuItemClick(MenuItem item) {


                        if (token.length() < 5) {
                                    *//*Intent goReg = new Intent(getApplicationContext(), Register.class);
                                   startActivity(goReg);*//*
                            Log.d("tag", "<5");
                        } else if (token.length() > 9) {

                            //item.setTitle("Logout");
//                            mi.setTitle("logout");
                            Log.d("tag", ">5");
                        }

                        switch (item.getItemId()) {
                            case R.id.item1:

                                Log.d("tag", "" + token);

                                if (token.length() < 5) {
                                    *//*Intent goReg = new Intent(getApplicationContext(), Register.class);
                                    startActivity(goReg);*//*
                                    Log.d("tag", "<5");
                                } else if (token.length() > 9) {

                                    //item.setTitle("Logout");

                                    Log.d("tag", ">5");
                                }
                                return true;
                            case R.id.item2:
                                //Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
                                Intent goSett = new Intent(Dashboard.this, Settings.class);
                                startActivity(goSett);
                                return true;
                            case R.id.item3:
                                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                                share.setType("text/plain");
                                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                share.putExtra(Intent.EXTRA_SUBJECT, "Share VyuSpot");
                                share.putExtra(Intent.EXTRA_TITLE, "This app helps at Disaster Times...");
                                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.worldmanager.laserclinics");

                                startActivity(Intent.createChooser(share, "Share link!"));

                                return true;

                            case R.id.item4:

                                Intent goHelp = new Intent(Dashboard.this, Help.class);
                                startActivity(goHelp);
                                return true;

                            default:
                                return true;
                        }

                    }
                });

                popup.show();*/
            }

        });





       // contx.setOnCreateContextMenuListener(this);






        voic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent govoic = new Intent(getApplicationContext(), VoiceAct.class);
                startActivity(govoic);


            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goD = new Intent(getApplicationContext(), SpotEmergency.class);
                startActivity(goD);

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goA = new Intent(getApplicationContext(), SpotAccident.class);
                startActivity(goA);

            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goN = new Intent(getApplicationContext(), SpotResourceNeed.class);
                startActivity(goN);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goV = new Intent(getApplicationContext(), View_Needs.class);
                startActivity(goV);

            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goM = new Intent(getApplicationContext(), MapView.class);
                startActivity(goM);

            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gps = new GpsT(Dashboard.this);
                if (gps.canGetLocation()) {
                    double latitude1 = gps.getLatitude();
                    double longitude2 = gps.getLongitude();
                    lati = String.valueOf(latitude1);
                    longi = String.valueOf(longitude2);
                    new SOSSendToServer().execute();

                    Log.e("TAG_LATI", "lat_long" + lati + longi);
                    //Log.d("tag","Latitude:\t" + lati + "\nLongitude:\t" + longi);
                } else {
                    SplashScreen sps = new SplashScreen();
                    sps.buildAlertMessageNoGps();

                    //gps.showSettingsAlert();
                }

            }


        });
    }





    private class SOSSendToServer extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new SweetAlertDialog(Dashboard.this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#FFE64A19"));
            pDialog.setTitleText("Sending Your location...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String json = "", jsonStr = "";
            try {

                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("latitude", lati);
                jsonObject.accumulate("longitude", longi);



                json = jsonObject.toString();

                return jsonStr = HttpUtils.makeRequest15(URL_SOS, json);
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            return null;

        }
        @Override
        protected void onPostExecute(String s) {
            Log.d("tag", "<-----rerseres---->" + s);
            super.onPostExecute(s);
            pDialog.dismiss();

            String to = "mbrsalman@gmail.com   ";
            String subject ="emergency";
            String message = "darun "+lati+longi;

            if(s==null)
            {
                Toast.makeText(getApplicationContext(), "send by email..." + lati + longi, Toast.LENGTH_LONG).show();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                // need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client"));

            }
            try {
                JSONObject jo = new JSONObject(s);

                String status = jo.getString("status");

                //String msg = jo.getString("message");
                Log.d("tag", "<-----Status----->" + status);


                if (status.equals("SUCCESS")) {

                    new SweetAlertDialog(Dashboard.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("SOS Alert")
                            .setContentText("Your Location Sent as SOS")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {

                                    sweetAlertDialog.cancel();
                                    Intent goD = new Intent(getApplicationContext(), Dashboard.class);
                                    startActivity(goD);


                                }
                            })
                            .show();


                    //Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                } else if (!status.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Retry or check your Network", Toast.LENGTH_LONG).show();
                } else {

                    new SweetAlertDialog(Dashboard.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Oops!")
                            .setContentText("Check your internet or try again")
                            .setConfirmText("OK")
                            .show();
                    //  Toast.makeText(getActivity(), "Check your internet or try again", Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }









    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opt_menu, menu);
        MenuItem bedMenuItem = menu.findItem(R.id.item1);


        if(token.length() <5)
        {
            bedMenuItem.setTitle("Register");

        }

        if(token.length() >5){
            bedMenuItem.setTitle("Logout");
        }


        /*if (token.length() < 5) {
            Log.d("tag", "<5");
         *//*   Intent goReg = new Intent(getApplicationContext(), Register.class);
            startActivity(goReg);*//*
        }
        else if (token.length() > 9) {

            bedMenuItem.setTitle("Logout");
            //menu.close();
            //mOptionsMenu.getItem(R.id.item1).setTitle("Logout");
            Log.d("tag", ">5");
        }
       else if(bedMenuItem.getTitle() =="Logout");
                bedMenuItem.setTitle("Register");*/

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:


                if(item.getTitle() == "Register")
                {
                    Intent goReg = new Intent(getApplicationContext(), Register.class);
                    startActivity(goReg);
                }
                else if(item.getTitle() == "Logout")
                {


                    new SweetAlertDialog(Dashboard.this,SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Do you want to Logout?")
                            .setConfirmText("Yes!")
                            .setCancelText("No")

                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    token = null;
                                    //welcome.setText("Welcome Guest");
                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("name");
                                    editor.remove("token");
                                    editor.commit();

                                    Intent goDas = new Intent(getApplicationContext(),Dashboard.class);
                                    startActivity(goDas);

                                    sDialog.dismiss();

                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            })
                            .show();

                }




               /* String token = prefs.getString("token","");
                if(token == null) {*/
           /*     if(item.getTitle() == "Logout"){

                    new SweetAlertDialog(Dashboard.this,SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Do you want to Logout?")
                            .setConfirmText("Yes!")
                            .setCancelText("No")

                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    item.setTitle("Logout");
                                    sDialog.dismiss();

                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            })
                            .show();
                }
*/
             /*   if(token.length() <5){
                    Intent goReg = new Intent(getApplicationContext(), Register.class);
                    startActivity(goReg);
                    Log.d("tagin","inside <5");

                }
                else{

                }*/
              /*  else if(token.length() >9){
                    //item.setTitle("Logout");
                    //bedMenuItem.setTitle("Register");
                    Log.d("tagin", "inside >5");
                }*/
               /* }
                else{
                    Toast.makeText(getApplicationContext(),"You Already Registered",Toast.LENGTH_LONG).show();
                }*/
                return true;
            case R.id.item2:
                //Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
                Intent goSett = new Intent(Dashboard.this, Settings.class);
                startActivity(goSett);
                return true;
            case R.id.item3:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Share VyuSpot");
                share.putExtra(Intent.EXTRA_TITLE, "This app helps at Disaster Times...");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.worldmanager.laserclinics");

                startActivity(Intent.createChooser(share, "Share link!"));

                return true;

            case R.id.item4:

                Intent goHelp = new Intent(Dashboard.this, Help.class);
                startActivity(goHelp);
                return true;

            default:
                return super.onContextItemSelected(item);

        }


    }

/*


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opt_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.item1:
               */
/* String token = prefs.getString("token","");
                if(token == null) {*//*


                if(token.length() <5){
                    Intent goReg = new Intent(getApplicationContext(), Register.class);
                    startActivity(goReg);
                    Log.d("tag","<5");
                }
                else if(token.length() >9){
                    item.setTitle("Logout");
                    Log.d("tag", ">5");
                }
               */
/* }
                else{
                    Toast.makeText(getApplicationContext(),"You Already Registered",Toast.LENGTH_LONG).show();
                }*//*

                return true;
            case R.id.item2:
                //Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
                Intent goSett = new Intent(Dashboard.this,Settings.class);
                startActivity(goSett);
                return true;
            case R.id.item3:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Share VyuSpot");
                share.putExtra(Intent.EXTRA_TITLE,"This app helps at Disaster Times...");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.worldmanager.laserclinics");

                startActivity(Intent.createChooser(share, "Share link!"));

                return true;

            case R.id.item4:

                Intent goHelp = new Intent(Dashboard.this,Help.class);
                startActivity(goHelp);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.opt_menu, menu);

        MenuItem bedMenuItem = menu.findItem(R.id.item1);





        if(token.length() <5)
        {
            bedMenuItem.setTitle("Register");
            //welcome.setText("Welcome Guest");
        }

        if(token.length() >5){
            bedMenuItem.setTitle("Logout");
        }



  /*      if (token.length() < 5) {
            Log.d("tag", "<5");
           *//* Intent goReg = new Intent(getApplicationContext(), Register.class);
            startActivity(goReg);*//*
        }
        else if (token.length() > 9) {

            bedMenuItem.setTitle("Logout");
            //mOptionsMenu.getItem(R.id.item1).setTitle("Logout");
            Log.d("tagg", ">5");
        }
        else if (bedMenuItem.getTitle() == "Logout"){
            bedMenuItem.setTitle("Register");
        }*/

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch (item.getItemId()) {


            case R.id.item1:


                if(item.getTitle() == "Register")
                {
                    Intent goReg = new Intent(getApplicationContext(), Register.class);
                    startActivity(goReg);
                }
                else if(item.getTitle() == "Logout")
                {


                    new SweetAlertDialog(Dashboard.this,SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Do you want to Logout?")
                            .setConfirmText("Yes!")
                            .setCancelText("No")

                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    token = null;
                                   //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
                                    //SharedPreferences.Editor editor = sharedPreferences.edit();
                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Dashboard.this);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("name");
                                    editor.remove("token");
                                    editor.commit();
                                    finish();
                                    Intent goDas = new Intent(getApplicationContext(),Dashboard.class);
                                    startActivity(goDas);

                                    sDialog.dismiss();

                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            })
                            .show();

                }








               /* String token = prefs.getString("token","");
                if(token == null) {*/


               // if(item.getTitle() == "Logout"){
                   /*final MenuItem item1;
                    item1 = item;

                    *//*new SweetAlertDialog(Dashboard.this,SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Do you want to Logout?")
                            .setConfirmText("Yes!")
                            .setCancelText("No")

                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    item1.setTitle("Register");
                                    sDialog.dismiss();

                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            })
                            .show();*//*
                }
*/
           /*     if(token.length() <5){
                    Intent goReg = new Intent(getApplicationContext(), Register.class);
                    startActivity(goReg);
                    Log.d("tagin","inside <5");
                }
                else if(token.length() >9){
                    //item.setTitle("Logout");
                    Log.d("tagin", "inside >5");
                }*/
               /* }
                else{
                    Toast.makeText(getApplicationContext(),"You Already Registered",Toast.LENGTH_LONG).show();
                }*/
                return true;
            case R.id.item2:
                //Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
                Intent goSett = new Intent(Dashboard.this,Settings.class);
                startActivity(goSett);
                return true;
            case R.id.item3:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Share VyuSpot");
                share.putExtra(Intent.EXTRA_TITLE,"This app helps at Disaster Times...");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.worldmanager.laserclinics");

                startActivity(Intent.createChooser(share, "Share link!"));

                return true;

            case R.id.item4:

                Intent goHelp = new Intent(Dashboard.this,Help.class);
                startActivity(goHelp);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }















    public void onBackPressed() {


        new SweetAlertDialog(Dashboard.this,SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Do you want to exit the Application?")
                .setConfirmText("Yes!")
                .setCancelText("No")

                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent i1 = new Intent(Intent.ACTION_MAIN);
                        i1.setAction(Intent.ACTION_MAIN);
                        i1.addCategory(Intent.CATEGORY_HOME);
                        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i1);
                        finish();

                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();

                    }
                })
                .show();




        /*final AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setMessage("Do you want to exit the Application?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i1 = new Intent(Intent.ACTION_MAIN);
                        i1.setAction(Intent.ACTION_MAIN);
                        i1.addCategory(Intent.CATEGORY_HOME);
                        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i1);
                        finish();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.create();
        builder.show();*/

    }


}
