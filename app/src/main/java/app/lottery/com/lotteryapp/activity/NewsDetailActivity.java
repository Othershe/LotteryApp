package app.lottery.com.lotteryapp.activity;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import app.lottery.com.lotteryapp.R;
import app.lottery.com.lotteryapp.data.NewsData;
import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity {
    private NewsData newsData;

    @BindView(R.id.gank_detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.gank_detail_webview)
    WebView mWebView;

    @BindView(R.id.gank_detail_progress)
    ProgressBar mProgressBar;

    private String html;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_gank_detail;
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    @Override
    protected void initData() {
        newsData = getIntent().getParcelableExtra("news_data");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(newsData.getUrl()).get();
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initWebView();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initWebView() {
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                settings.setBlockNetworkImage(false);
            }
        });
//        mWebView.loadUrl(newsData.getUrl());
        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    private void initToolbar() {
        String desc = newsData.getTitle();
        desc = desc.length() > 10 ? desc.substring(0, 16) + "..." : desc;
        mToolbar.setTitle(desc);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }
}
