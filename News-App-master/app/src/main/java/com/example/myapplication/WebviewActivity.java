package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity extends AppCompatActivity {
    private WebView mWebView;
    String url ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        url = getIntent().getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        mWebView.setWebViewClient(new WebViewClient());

        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(url);

    }
}
