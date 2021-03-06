package com.fanda.zeng.arouterdemo.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des  //递自定义对象会报错，需要对象解析服务来处理 SerializationService
 */

@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    @Override
    public void init(Context context) {
        
    }

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return JSON.parseObject(input,clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return JSON.toJSONString(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return JSON.parseObject(input,clazz);
    }
}
