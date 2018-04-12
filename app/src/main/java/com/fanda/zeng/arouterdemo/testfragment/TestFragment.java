package com.fanda.zeng.arouterdemo.testfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fanda.zeng.arouterdemo.base.BaseFragment;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des fragment 跳转界面用例
 */

@Route(path = "/test/TestFragment")
public class TestFragment extends BaseFragment{

    @Autowired
    public String name ;

    public TestFragment() {
    }

    public static TestFragment getInstance(Activity activity) {
       return (TestFragment) ARouter.getInstance().build("/test/TestFragment").withString("name","fanda").navigation(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("fragment 跳转界面用例"+"-----"+name);
        return textView;
    }
}
