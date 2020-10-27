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
//        imageView = findViewById(R.id.image_view);
//        textView = findViewById(R.id.text_view);
        webView = findViewById(R.id.webview);
        Bundle bundle = getIntent().getExtras();
        Integer logo  = bundle.getInt("logo");
        String name = bundle.getString("name");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(name);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        imageView.setImageResource(logo);
//        textView.setText(name);
    }
}