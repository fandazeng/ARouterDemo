package com.fanda.zeng.arouterdemo.testactivity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fanda.zeng.arouterdemo.R;
import com.fanda.zeng.arouterdemo.base.BaseActivity;

/**
 * Created by 曾凡达 on 2018/4/11.
 * des
 */

@Route(path = "/test/webview")
public class TestWebviewActivity extends BaseActivity {

    @Autowired
    String url ;

    private WebView mWebview ;

    public static void launch(Activity activity) {
        ARouter.getInstance().build("/test/webview").withString("url","file:///android_asset/scheme-test.html").navigation(activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_webview);
        mWebview = findViewById(R.id.webview);
        mWebview.loadUrl(url);
    }
}
