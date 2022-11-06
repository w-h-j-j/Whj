package com.mingrisoft.whj;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.tencent.smtt.sdk.QbSdk;

import com.tencent.smtt.sdk.WebView;


public class MainActivity4 extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        webView=findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.baidu.com");


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        QbSdk.clearAllWebViewCache(this,true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}