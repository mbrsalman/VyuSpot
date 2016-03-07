package com.startemplan.vyuspot;

import android.app.Activity;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


import android.widget.ImageButton;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
/*import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;*/

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.rey.material.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Register extends Activity {

    ProgressDialog prgDialog;
   // RequestParams params = new RequestParams();
    GoogleCloudMessaging gcmObj;
    Context applicationContext;
    SweetAlertDialog pDialog;
    ImageButton cont,conti;
    String numbers;

    EditText  name,number,email,emuc1,cont2,cont3;


    String username,usermail,usernumber,gcmregId,device_id,emgc1,con2,con3,token=null,emgcont,emgmail,contact;
    Button send;
    MaterialRippleLayout mtp;
    TelephonyManager tm;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static String URLL = "http://sqindia01.cloudapp.net/vyuspot/api/v1/user/register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        applicationContext = getApplicationContext();



        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        gcmregId = prefs.getString("regId", "");


        cont = (ImageButton) findViewById(R.id.cont);
        conti = (ImageButton) findViewById(R.id.cont2);


        tm=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        device_id=tm.getDeviceId();
        send = (Button)findViewById(R.id.send);

        name = (EditText)findViewById(R.id.namem);
        number = (EditText) findViewById(R.id.contac);
        email = (EditText) findViewById(R.id.email);
        emuc1 = (EditText) findViewById(R.id.emu1);
        cont2 = (EditText) findViewById(R.id.emu2);
        cont3 = (EditText) findViewById(R.id.emu3);


        mtp = (MaterialRippleLayout) findViewById(R.id.ripple);




        prefs = PreferenceManager.getDefaultSharedPreferences(Register.this);

         edit = prefs.edit();





        mtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                startActivityForResult(pickContactIntent, 1);

            }
        });


        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                startActivityForResult(pickContactIntent, 2);


            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                username = name.getText().toString();
                usermail = email.getText().toString();
                usernumber = number.getText().toString();
                emgcont = emuc1.getText().toString();
                emgmail = cont2.getText().toString();
                contact = cont3.getText().toString();





                if ( !username.isEmpty() && !usermail.isEmpty() && !usernumber.isEmpty() && !emgcont.isEmpty() && !emgmail.isEmpty()) {

                   // Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();

                    if ( Utility.numvalid(usernumber) && Utility.numvalid(emgcont))
                    {

                        if ( Utility.validate(usermail) && Utility.validate(emgmail))
                        {
                            if (!TextUtils.isEmpty(gcmregId)) {


                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Register.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("test", "12345");
                                editor.putString("pus","54321");
                                editor.putString("name",username);
                                editor.commit();


                                Intent i = new Intent(applicationContext, GreetingActivity.class);
                                i.putExtra("regId", gcmregId);
                                startActivity(i);
                                finish();
                            } else {
                                gotoCheck();
                            }
                        }
                        else
                        {
                            new SweetAlertDialog(Register.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Alert")
                                    .setContentText("Email id is Not valid! ")
                                    .setConfirmText("OK")
                                    .show();
                        }

                    }
                    else
                    {
                        new SweetAlertDialog(Register.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Alert")
                                .setContentText("Phone Number is Not valid! ")
                                .setConfirmText("OK")
                                .show();
                    }


                }

                else {

                    new SweetAlertDialog(Register.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Alert")
                            .setContentText("Please enter all details! ")
                            .setConfirmText("OK")
                            .show();
                }

            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to


        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                numbers = cursor.getString(column);
                emuc1.setText(numbers);


                // Do something with the phone number...
            }
        }
        else if (requestCode == 2) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                numbers = cursor.getString(column);
                cont3.setText(numbers);

                // Do something with the phone number...
            }
        }
    }





    private void gotoCheck() {

        pDialog = new SweetAlertDialog(Register.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#FFE64A19"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        if(checkPlayServices()) {
            registerInBackground();
        }
    }




    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging.getInstance(applicationContext);
                    }
                    gcmregId = gcmObj.register(ApplicationConstants.GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + gcmregId;
                    Log.d("tag",gcmregId);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if (!TextUtils.isEmpty(gcmregId)) {

                   Toast.makeText(
                            applicationContext,
                            "Registered with GCM Server successfully.\n\n"
                                    + msg, Toast.LENGTH_SHORT).show();

                    Log.d("tag1", gcmregId);


                    storegcmregIdinServer();


                } else {
                    Toast.makeText(
                            applicationContext,
                            "Reg ID Creation Failed.\n\nEither you haven't enabled Internet or GCM server is busy right now. Make sure you enabled Internet and try registering again after some time."
                                    + msg, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }





    private class JSONParse extends AsyncTask<String, Void, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            Log.d("tags", username + usermail + usernumber);

            /*pDialog = new ProgressDialog(Register.this);
            // pDialog.setMessage("Registered Please Wait ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/

        }

        @Override
        protected String doInBackground(String... params) {
            String json = "", jsonStr = "";
            try {

                //Toast.makeText(getApplicationContext(),"3rdone working",Toast.LENGTH_LONG).show();
                Log.d("tags", "doinbackgroudn");
                //{"userName"="Mufeed","email":"mufeedk@gmail.com","phone":"12332343","deviceId":"dsfs1231231"}
                // 3. build jsonObject
                //pDialog.setMessage("Loading...");
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("userName", username);
                jsonObject.accumulate("email", usermail);
                jsonObject.accumulate("phone", usernumber);
                jsonObject.accumulate("deviceId", "device_id");
               // jsonObject.accumulate("gcmregId",gcmregId);


                json = jsonObject.toString();


                Log.d("tag","beforehttp");
                Log.d("tag",URLL);
                return jsonStr = HttpUtils.makeRequest1(URLL, json);
            } catch (Exception e) {
                Log.d("loge", e.getLocalizedMessage());
            }

            return null;

        }
        @Override
        protected void onPostExecute(String s) {
            Log.d("tag", "<-----rerseres---->" + s);
            super.onPostExecute(s);
           /* pDialog.setMessage("Successfully Registered..");
            pDialog.dismiss();*/
          //  storeRegIdinSharedPref();


           /* if(s==null)
            {
                Toast.makeText(getApplicationContext(),"server busy s",Toast.LENGTH_LONG).show();
            }*/
         /*   else {*/
       /*         try {
                    JSONObject jo = new JSONObject(s);

                    String status = jo.getString("status");

                    //String msg = jo.getString("message");
                    Log.d("tag", "<-----Status----->" + status);


                    if (status.equals("SUCCESS")) {

                        new SweetAlertDialog(Register.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Message Alert")
                                .setContentText("Spot Added Sucessfully")
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

                        new SweetAlertDialog(Register.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Oops!")
                                .setContentText("Check your internet or try again")
                                .setConfirmText("OK")
                                .show();
                        //  Toast.makeText(getActivity(), "Check your internet or try again", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            //}

        }
    }





    private void storegcmregIdinServer()
    {


     new AsyncTask<String, Void, String> (){


            protected void onPreExecute() {
                super.onPreExecute();
                Log.d("tag", "s1");
                            }

            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub

                String json = "", jsonStr = "";

                try {

                    Map<String, String> hashmapu = new HashMap<String, String>();
                    hashmapu.put("emailId", usermail);
                    hashmapu.put("regId", gcmregId);

                    post(ApplicationConstants.APP_SERVER_URL, hashmapu);





            /*      JSONObject jsonObject = new JSONObject();

                    jsonObject.accumulate("emailId", usermail);
                    jsonObject.accumulate("regId", gcmregId);
                    json = jsonObject.toString();*/
                    return jsonStr;// = HttpUtils.makeRequest1(ApplicationConstants.APP_SERVER_URL, json);
                } catch (Exception e) {
                    Log.d("tagInputStream", e.getLocalizedMessage());
                }

                return null;

            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("tag", usermail + gcmregId);
                Log.d("tag", "<-----rerseres---->" + s);
                register();
                super.onPostExecute(s);
            }

        }.execute();


       // prgDialog.show();

    /*    params.put("gcmregId", gcmregId);
        params.put("emailId", usermail);

        Log.d("tag3", gcmregId+usermail);


        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(ApplicationConstants.APP_SERVER_URL, params,
                new AsyncHttpResponseHandler() {
                    // When the response returned by REST has Http
                    // response code '200'
                    @Override
                    public void onSuccess(String response) {
                        // Hide Progress Dialog
                      //  prgDialog.hide();
                        *//*if (prgDialog != null) {
                            prgDialog.dismiss();
                        }*//*
                        Toast.makeText(applicationContext, "Reg Id shared successfully with Web App ", Toast.LENGTH_LONG).show();
                        new JSONParse().execute();
                      *//*  Intent i = new Intent(applicationContext, GreetingActivity.class);
                        i.putExtra("gcmregId", gcmregId);
                        startActivity(i);
                        finish();*//*
                    }

                    // When the response returned by REST has Http
                    // response code other than '200' such as '404',
                    // '500' or '403' etc
                    @Override
                    public void onFailure(int statusCode, Throwable error,
                                          String content) {
                        // Hide Progress Dialog
                        *//*prgDialog.hide();
                        if (prgDialog != null) {
                            prgDialog.dismiss();
                        }*//*
                        // When Http response code is '404'
                        if (statusCode == 404) {
                            Toast.makeText(applicationContext,
                                    "Requested resource not found",
                                    Toast.LENGTH_LONG).show();
                        }
                        // When Http response code is '500'
                        else if (statusCode == 500) {
                            Toast.makeText(applicationContext,
                                    "Something went wrong at server end",
                                    Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else {
                            Toast.makeText(
                                    applicationContext,
                                    "Unexpected Error occcured! [Most common Error: Device might "
                                            + "not be connected to Internet or remote server is not up and running], check for other errors as well",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
    }





    private void register()
    {


        new AsyncTask<String, Void, String> (){


            protected void onPreExecute() {
                super.onPreExecute();
                Log.d("tag", "last");
            }

            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub

                String json = "", jsonStr = "";

                try {

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.accumulate("userName", username);
                    jsonObject.accumulate("email", usermail);
                    jsonObject.accumulate("phone", usernumber);
                    jsonObject.accumulate("deviceId", "device_22id00003");
                    jsonObject.accumulate("emergencyContact", emgcont);
                    jsonObject.accumulate("emergencyContact1", emgmail);
                    jsonObject.accumulate("emergencyContact2", contact);
                    json = jsonObject.toString();
                    return jsonStr = HttpUtils.makeRequest1(URLL, json);
                } catch (Exception e) {
                    Log.d("InputStream", e.getLocalizedMessage());
                }

                return null;

            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("tag", username + usermail + usernumber + device_id);
                pDialog.dismiss();
                super.onPostExecute(s);
                Log.d("tag", "<-----rerseres---->" + s);





                try {


                    JSONObject jo = new JSONObject(s);

                    String status = jo.getString("message");

                    String msg = jo.getString("message");
                    Log.d("tag", "<-----Status----->" + status);



                    if(status.equals("User added successfully"))
                    {
                        JSONObject jo1 = new JSONObject(s);
                        String datab = jo1.getString("data");
                        JSONObject jo2 = new JSONObject(datab);
                        token = jo2.getString("authToken");

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Register.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("test", "12345");
                        editor.putString("pus", "54321");
                        editor.putString("token", token);
                        Log.d("tag", "tok" + token);
                        editor.putString("name", username);
                        editor.commit();



                       // edit.remove("token");

                        storeRegIdinSharedPref();


                                Intent i = new Intent(getApplicationContext(), GreetingActivity.class);
                                i.putExtra("regId", gcmregId);
                                startActivity(i);
                                finish();


                    }
                    else if(status.equals("User Device already registered"))
                    {


                        JSONObject jo3 = new JSONObject(s);
                        JSONArray jsonarray = jo3.getJSONArray("data");

                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject joo = jsonarray.getJSONObject(i);

                            String user = joo.getString("userExist");
                            token = joo.getString("authToken");


                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Register.this);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("test", "12345");
                            editor.putString("pus","54321");
                            editor.putString("token", token);
                            Log.d("tag","tok"+token);
                            editor.putString("name", username);
                            editor.commit();


                            storeRegIdinSharedPref();


                                    Intent ii = new Intent(getApplicationContext(), GreetingActivity.class);
                                    ii.putExtra("regId", gcmregId);
                                    startActivity(ii);
                                    finish();


                        }

                    }

                    Log.d("tag", "<-----Status----->" + token);
                }
                catch (JSONException e) {

                    e.printStackTrace();
                }
                //String msg = jo.getString("message");


            /*    try {
                    JSONObject jo = new JSONObject(s);

                    String status = jo.getString("status");

                    //String msg = jo.getString("message");
                    Log.d("tag", "<-----Status----->" + status);


                    if (status.equals("SUCCESS")) {

                        token = jo.getString("authToken");
                        Log.d("tagt",token);


                        Intent i = new Intent(getApplicationContext(),GreetingActivity.class);
                        i.putExtra("regId", gcmregId);
                        startActivity(i);
                        finish();


                    }  else {

                        new SweetAlertDialog(Register.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Oops!")
                                .setContentText("Check your internet or try again")
                                .setConfirmText("OK")
                                .show();
                        //  Toast.makeText(getActivity(), "Check your internet or try again", Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/






               // new JSONParse().execute();

            }

        }.execute();

    }




    private void storeRegIdinSharedPref() {

        Log.d("taglast", "worked");


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Register.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("regId",gcmregId);
        editor.putString("eMailId", usermail);
        editor.commit();



 /*       SharedPreferences.Editor editor = prefs.edit();


        editor.putString("name", username);
        editor.putString("mail", usermail);
        editor.putString("number", usernumber);
        editor.putString("gcmid", gcmregId);
        editor.putString("deviceid", device_id);
        editor.putString("token", token);

        editor.commit();

        String a1 = prefs.getString("name", "");
        String a2 = prefs.getString("mail","");
        String a3 = prefs.getString("number","");
        String a4 = prefs.getString("gcmid","");
        String a5 = prefs.getString("deviceid","");
        String a6 = prefs.getString("token","");
        Log.d("tag", token);



        Log.d("tag", a1);
        Log.d("tag", a2);
        Log.d("tag", a3);
        Log.d("tag", a4);
        Log.d("tag", a5);
        Log.d("tag", a6);*/


    }



    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // When Play services not found in device
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                // Show Error dialog to install Play services
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(
                        applicationContext,"This device doesn't support Play services, App will not work normally",Toast.LENGTH_LONG).show();
                pDialog.setTitle("App wont work without google play services");
                pDialog.dismiss();

                finish();
            }
            return false;
        } else {
            Toast.makeText(applicationContext,"This device supports Play services, App will work normally", Toast.LENGTH_LONG).show();
        }
        return true;
    }


    private static void post(String endpoint, Map<String, String> params)
            throws IOException {

        URL url;
        try {
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("taginvalid url: " + endpoint);
        }
        StringBuilder bodyBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        // constructs the POST body using the parameters
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            bodyBuilder.append(param.getKey()).append('=')
                    .append(param.getValue());
            if (iterator.hasNext()) {
                bodyBuilder.append('&');
            }
        }
        String body = bodyBuilder.toString();
        Log.d("tag", "Posting '" + body + "' to " + url);
        byte[] bytes = body.getBytes();
        HttpURLConnection conn = null;
        try {
            Log.d("URL", "> " + url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setFixedLengthStreamingMode(bytes.length);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            // post the request
            OutputStream out = conn.getOutputStream();
            out.write(bytes);
            out.close();
            // handle the response
            int status = conn.getResponseCode();
            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }




}