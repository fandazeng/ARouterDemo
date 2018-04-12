package com.fanda.zeng.arouterdemo.service;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by 曾凡达 on 2018/4/12.
 * des 测试服务实现类
 */

@Route(path = "/service/testService")
public class TestServiceImpl implements TestService {

    Context mContext;

    @Override
    public void test(String content) {
        Toast.makeText(mContext, "Hello : " + content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
