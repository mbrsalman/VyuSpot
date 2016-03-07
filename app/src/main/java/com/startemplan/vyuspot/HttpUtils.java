package com.startemplan.vyuspot;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by TekCampuz on 9/18/2015.
 */
public class HttpUtils {

    public static final String TAG = "tagHttpUtils";
String s1="";
    public static String makeRequest(String url, String json) {
        Log.v(TAG, "URL-->" + url);
        Log.v(TAG, "input-->" + json);


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("sessionToken","61b4d6fdf60218cdb2f3f27a72e4edcf");
            //text/html
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

            // receive response as inputStream
            InputStream inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                String result = convertInputStreamToString(inputStream);
                Log.v(TAG, "output-->" + result);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String makeRequest1(String url, String json) {
        Log.v(TAG, "URL-->" + url);
        Log.v(TAG, "input-->" + json);


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));

            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

            // receive response as inputStream
            InputStream inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                String result = convertInputStreamToString(inputStream);
                Log.v(TAG, "output-->" + result);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String makeRequest2(String url, String json,String AuthToken) {
        Log.v(TAG, "URL-->" + url);
        Log.v(TAG, "input-->" + json);


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("sessionToken",AuthToken);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json"); //text/html
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

            // receive response as inputStream
            InputStream inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                String result = convertInputStreamToString(inputStream);
                Log.v(TAG, "output-->" + result);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




    public static String makeRequest3(String url, String json) {
        Log.v(TAG, "URL-->" + url);
        Log.v(TAG, "input-->" + json);


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));
            //httpPost.setHeader("sessionToken",AuthToken);
           // httpPost.setHeader("Accept", "application/json");
          //  httpPost.setHeader("Content-type", "application/json"); //text/html
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

            // receive response as inputStream
            InputStream inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                String result = convertInputStreamToString(inputStream);
                Log.d("tag", "outputtttttttttt-->" + result);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String makeRequest55(String url, String json) {
        Log.v(TAG, "URL-->" + url);
        Log.v(TAG, "input-->" + json);


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            //text/html
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

            // receive response as inputStream
            InputStream inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                String result = convertInputStreamToString(inputStream);
                Log.v(TAG, "output-->" + result);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String makeRequest15(String url, String json) {
        Log.v(TAG, "URL-->" + url);
        Log.v(TAG, "input-->" + json);


        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(json));
            httpPost.setHeader("sessionToken","61b4d6fdf60218cdb2f3f27a72e4edcf");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json"); //text/html
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);

            // receive response as inputStream
            InputStream inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if (inputStream != null) {
                String result = convertInputStreamToString(inputStream);
                Log.v(TAG, "output-->" + result);
                return result;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }















    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Header adding multiple parameter>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();

        System.out.println(" OUTPUT -->" + result);


        return result;

    }
}
