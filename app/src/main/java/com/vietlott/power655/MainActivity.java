package com.vietlott.power655;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen WebView
        webView = new WebView(this);
        webView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        setContentView(webView);

        WebSettings settings = webView.getSettings();

        // JavaScript
        settings.setJavaScriptEnabled(true);

        // localStorage — giữ dữ liệu sau khi tắt app
        settings.setDomStorageEnabled(true);

        // Cho phép load file HTML từ assets
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        // Database (dự phòng thêm)
        settings.setDatabaseEnabled(true);

        // Viewport fit màn hình điện thoại
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        // Zoom
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        // Cache offline
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        // Load file HTML từ assets
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Nút Back điều hướng trong WebView
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Dừng WebView đúng cách khi app bị đóng
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }
}
