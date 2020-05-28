package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    WebView webview;
    Button button;
    AlertDialog.Builder alert;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;


        webview = (WebView) findViewById(R.id.webview);
//        webview = new WebView(this);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://172.16.61.120:8000/1001-Technical%20Support-20200324.mp4");


        alert = new AlertDialog.Builder(this);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview = new WebView(getApplicationContext());
                WebSettings webSettings = webview.getSettings();
                webSettings.setJavaScriptEnabled(true);

                webview.setWebViewClient(new WebViewClient());
//                webview.loadUrl("https://www.w3schools.com/html/mov_bbb.mp4");
                webview.loadUrl("http://172.16.61.120:8000/test.mp4");




//                alert.setTitle("Title here");
//                alert.setView(webview);
//                alert.show();
//                ViewGroup.LayoutParams lp = webview.getLayoutParams();
//                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//                webview.setLayoutParams(lp);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                AlertDialog alertDialog = alert.create();
                alertDialog.setView(webview);
                alertDialog.show();
                alertDialog.getWindow().setLayout(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                alertDialog.getWindow().setLayout(displayMetrics.widthPixels, displayMetrics.heightPixels-50);

                ViewGroup.LayoutParams lp = webview.getLayoutParams();
//                lp.width = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.width = displayMetrics.widthPixels;
                lp.height =displayMetrics.heightPixels-150;
                webview.setLayoutParams(lp);

            }
        });
    }
}
