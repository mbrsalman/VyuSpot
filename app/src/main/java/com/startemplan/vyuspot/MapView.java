package com.startemplan.vyuspot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ClientCertRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.balysv.materialripple.MaterialRippleLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MapView extends Activity {
    MaterialRippleLayout mtp;
    WebView webview;
    SweetAlertDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        webview = (WebView) findViewById(R.id.webView);

        mtp = (MaterialRippleLayout) findViewById(R.id.ripple);
        pDialog = new SweetAlertDialog(MapView.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#FFE64A19"));
        pDialog.setTitleText("Loading Map");
        pDialog.setCancelable(false);
        pDialog.show();

        mtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDashboard = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(goDashboard);
                finish();
            }
        });



        webview.loadUrl("http://sqindia01.cloudapp.net/vyumaps/");
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new MapviewClient());


/*

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {



                view.loadUrl(url);

                return false;
            }
        });*/



    }



    private class MapviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            pDialog.dismiss();
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }
    }





}