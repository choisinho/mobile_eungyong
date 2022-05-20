package com.example.project6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtUrl;
    Button btnGo, btnBack;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단웹브라우저");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.web);

        edtUrl = (EditText) findViewById(R.id.edtUrl);
        btnGo = (Button) findViewById(R.id.btnGo);
        btnBack = (Button) findViewById(R.id.btnBack);
        web = (WebView) findViewById(R.id.webView1);

        web.setWebViewClient(new CookWebView());

        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);

        edtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtUrl.getText().toString();
                if(url.startsWith("http://") ||  url.startsWith("https://"))
                    web.loadUrl(url);
                else
                    web.loadUrl("http://"+url);
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = edtUrl.getText().toString();
                if(url.startsWith("http://") ||  url.startsWith("https://"))
                    web.loadUrl(url);
                else
                    web.loadUrl("http://"+url);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                web.goBack();
            }
        });
    }

    class CookWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
