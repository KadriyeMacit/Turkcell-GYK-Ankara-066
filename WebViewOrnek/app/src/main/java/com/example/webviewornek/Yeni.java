package com.example.webviewornek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Yeni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni);

        // xml dosyasına eklediğimiz webview'e id eklemeyi unutmuyoruz.
        WebView webView = (WebView) findViewById(R.id.webView);

        // webview kullanabilmemiz için manifest dosyasında internet izni önemli.
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://gelecegiyazanlar.turkcell.com.tr");

    }
}
