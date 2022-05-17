package com.example.movieclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;


public class WebActivity extends AppCompatActivity {

    WebView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> murls2 = getIntent().getStringArrayListExtra("ur");
        setContentView(R.layout.activity_web);
        int intValue = getIntent().getIntExtra("u", 0);
        view1 = (WebView) findViewById(R.id.webView1);
        view1.loadUrl(murls2.get(intValue));

        view1.getSettings().setJavaScriptEnabled(true);

        view1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

    }
}