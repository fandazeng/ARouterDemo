package com.fanda.zeng.arouterdemo.testactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 曾凡达 on 2018/4/11.
 * des 用于监听Scheme事件,之后直接把url传递给ARouter即可
 */

public class SchemeFilterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ARouter.getInstance().build(getIntent().getData()).navigation();
        finish();
    }
}
