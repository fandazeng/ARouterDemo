package com.fanda.zeng.arouterdemo.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by 曾凡达 on 2018/4/11.
 * des 全局降级策略
 */

@Route(path = "/service/degrade")
public class DegradeServiceImpl implements DegradeService {

    @Override
    public void onLost(Context context, Postcard postcard) {
        ARouter.getInstance().build("/test/Test1Activity").navigation();
    }

    @Override
    public void init(Context context) {

    }
}
