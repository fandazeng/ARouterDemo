package com.fanda.zeng.arouterdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //依赖注入参数或服务,如果没有，参数需要手动 getIntent 获取
        ARouter.getInstance().inject(this);
    }
}
