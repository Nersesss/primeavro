package com.su.primeavto.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.su.primeavto.BuildConfig;
import com.su.primeavto.Constants;
import com.su.primeavto.R;

public class WebViewPaymentActivity extends AppCompatActivity {

    private static final String INTENT_KEY_FULL_PRICE = "INTENT_KEY_FULL_PRICE";
    private static final String INTENT_KEY_TITLE = "INTENT_KEY_TITLE";
    private static final String INTENT_KEY_BODY = "INTENT_KEY_BODY";

    public static void start(Context context, int fullPrice, String title, String body) {
        Intent intent = new Intent(context, WebViewPaymentActivity.class);
        intent.putExtra(INTENT_KEY_FULL_PRICE, fullPrice);
        intent.putExtra(INTENT_KEY_TITLE, fullPrice);
        intent.putExtra(INTENT_KEY_BODY, fullPrice);
        context.startActivity(intent);
    }

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int fullPrice = getIntent().getIntExtra(INTENT_KEY_FULL_PRICE, 0);
        String title = getIntent().getStringExtra(INTENT_KEY_TITLE);
        String body = getIntent().getStringExtra(INTENT_KEY_BODY);
        setContentView(R.layout.activity_web_view_payment);
        webView = findViewById(R.id.web_view);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(BuildConfig.PAYMENT_URL
                + "?token=" + Constants.TOKEN
                + "&amount=" + fullPrice
                + "&title=" + title
                + "&body=" + body
        );
    }
}