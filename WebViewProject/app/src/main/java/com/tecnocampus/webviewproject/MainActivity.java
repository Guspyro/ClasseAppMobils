package com.tecnocampus.webviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);

        //webView.loadUrl("https://www.TriunityStudios.com");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false; // Permet carregar URLs dins de la web (clicks a links, ...) que hi han dins de la web. False = es fa el procediment per defecte.

                /*if (request.getUrl().getHost().contains("github.com")){
                    return false; // Si la URL és de "github.com", fer el procediment nomral
                }
                return true; // Si la URL no és de "github.com", no fer res*/
            }
        });

        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.loadUrl("file:///android_asset/index.html");
    }

    public class WebAppInterface {
        Context context;

        WebAppInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void showToast(String content) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }
}
