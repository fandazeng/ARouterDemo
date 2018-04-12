package com.fanda.zeng.arouterdemo.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by 曾凡达 on 2018/4/12.
 * des 测试服务
 */

public interface TestService extends IProvider {
    void test(String content);
}
