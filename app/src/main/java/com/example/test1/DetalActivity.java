package com.example.test1;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);
        webView = findViewById(R.id.webview);
        Bundle bundle = getIntent().getExtras();
        String linkWeb = bundle.getString("link");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(linkWeb);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}