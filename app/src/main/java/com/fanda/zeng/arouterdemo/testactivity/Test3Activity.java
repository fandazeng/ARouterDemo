package com.fanda.zeng.arouterdemo.testactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fanda.zeng.arouterdemo.R;
import com.fanda.zeng.arouterdemo.base.BaseActivity;
import com.fanda.zeng.arouterdemo.bean.TestObj;
import com.fanda.zeng.arouterdemo.bean.TestParcelable;

import java.util.List;
import java.util.Map;

/**
 * Created by 曾凡达 on 2018/4/9.
 * des 带参数的界面跳转测试用例
 */

// 在支持路由的页面上添加注解(必须写)
// 这里的路径需要注意的是至少需要有两级，/xx/xx，名字你自己起
@Route(path = "/test/Test3Activity")
public class Test3Activity extends BaseActivity {

    @Autowired(name = "teacherName")  //别名，如果设置了 name 这个属性，则传参时需要用别名
    protected String name ;  //不能设置为 private

    @Autowired  //需要添加该注解才会自动注入
    public int age ;

    @Autowired
    public TestParcelable testPac ;

    @Autowired
    public TestObj testObj;

    @Autowired
    public List<TestObj> testObjList;

    @Autowired
    public Map<String ,List<TestObj>> testMap;

    private TextView mContent ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_3);
        mContent = findViewById(R.id.tv_content);

//        mContent.setText(getIntent().getStringExtra("teacherName"));

        String params = String.format(
                "name=%s,\n age=%s,\n testPac=%s,\n testObj=%s, \n testObjList=%s, \n testMap=%s",
                name,
                age,
                testPac,
                testObj,
                testObjList,
                testMap
        );

        String extra = getIntent().getStringExtra("extra");
        mContent.setText(testPac.name+"---"+testPac.sex+"----extra---"+extra);
    }
}
