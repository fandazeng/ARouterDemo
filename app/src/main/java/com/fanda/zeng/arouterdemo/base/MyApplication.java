package com.fanda.zeng.arouterdemo.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();// 打印日志
        ARouter.openDebug();// 开启调试模式(如果在InstantRun(就是AndroidStudio2.0以后新增的一个可以减少很多编译时间的运行机制)模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
    }
}
