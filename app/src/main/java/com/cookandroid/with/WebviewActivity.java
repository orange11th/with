package com.cookandroid.with;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity extends AppCompatActivity {

    private WebView webview;
    private Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        init_webView();
        handler = new Handler();

    }

    public void init_webView() {
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.addJavascriptInterface(new AndroidBridge(), "TestApp");
        //webview.setWebChromeClient(new WebChromeClient());
        webview.setWebChromeClient(new myChromeClient());
        webview.loadUrl("http://3.36.66.178/address.php");
    }
    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //daum_result.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                    init_webView();
                }
            });
        }
    }
    private class myChromeClient extends  WebChromeClient {
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView newWebView = new WebView(WebviewActivity.this);
            WebSettings webSettings = newWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            final Dialog dialog = new Dialog(WebviewActivity.this);
            dialog.setContentView(newWebView);
            dialog.show();

            newWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onCloseWindow(WebView window) {
                    dialog.dismiss();
                }
            });

            ((WebView.WebViewTransport) resultMsg.obj).setWebView(newWebView);
            resultMsg.sendToTarget();
            return true;
        }
    }
}