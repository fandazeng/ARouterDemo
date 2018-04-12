package com.fanda.zeng.arouterdemo.testactivity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fanda.zeng.arouterdemo.R;
import com.fanda.zeng.arouterdemo.base.BaseActivity;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des 简单不带参数的界面跳转测试用例
 */

// 在支持路由的页面上添加注解(必须写)
// 这里的路径需要注意的是至少需要有两级，/xx/xx，名字你自己起
@Route(path = "/test/Test1Activity")
public class Test1Activity extends BaseActivity {

    public static void launch(Activity activity) {
        Uri uri = Uri.parse("http://www.wmzx.com/test/Test1Activity");
        ARouter.getInstance().build(uri).navigation(activity);


//        ARouter.getInstance().build("/test/Test1Activity").navigation(activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_1);
    }
}
