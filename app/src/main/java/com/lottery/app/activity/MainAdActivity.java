package com.lottery.app.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lottery.app.R;

import butterknife.BindView;

public class MainAdActivity extends BaseActivity {

    @BindView(R.id.main_webview)
    WebView mWebView;

    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main_ad;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
//        webSettings.setBlockNetworkImage(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.loadUrl(getIntent().getStringExtra("ad_url"));
    }

    @Override
    protected void initData() {

    }

    private class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//            if (mProgressBar == null) {
//                return;
//            }
//
//            if (newProgress == 100) {
//                mProgressBar.setVisibility(View.GONE);
//            } else {
//                mProgressBar.setVisibility(View.VISIBLE);
//                mProgressBar.setProgress(newProgress);
//            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
