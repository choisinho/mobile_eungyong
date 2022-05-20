package com.example.tabswipeevent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View frag3 = inflater.inflate(R.layout.frag3, container, false);

        EditText edtUrl = frag3.findViewById(R.id.edtUrl);
        Button btnGo = frag3.findViewById(R.id.btnGo);
        Button btnBack = frag3.findViewById(R.id.btnBack);
        WebView web = frag3.findViewById(R.id.webView1);

        web.setWebViewClient(new CookWebView());
        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);
        webSet.setJavaScriptEnabled(true);

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

        return frag3;
    }

    class CookWebView extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
