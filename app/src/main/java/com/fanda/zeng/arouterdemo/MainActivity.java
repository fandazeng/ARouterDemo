package com.fanda.zeng.arouterdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fanda.zeng.arouterdemo.base.BaseActivity;
import com.fanda.zeng.arouterdemo.bean.TestObj;
import com.fanda.zeng.arouterdemo.bean.TestParcelable;
import com.fanda.zeng.arouterdemo.service.TestService;
import com.fanda.zeng.arouterdemo.testactivity.Test1Activity;
import com.fanda.zeng.arouterdemo.testactivity.Test2Activity;
import com.fanda.zeng.arouterdemo.testactivity.Test3Activity;
import com.fanda.zeng.arouterdemo.testactivity.TestWebviewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mSimpleWithoutParams;
    private Button mWithParams;
    private Button mSimpleWithoutParamsForResult;
    private Button mFindFragment;
    private Button mWebview;
    private Button mUserService;

    private static Activity activity;

    @Autowired(name = "/service/testService")
    TestService testService;

    public static Activity getThis() {
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initViews();
        initClicks();
    }

    private void initClicks() {
        mSimpleWithoutParams.setOnClickListener(this);
        mWithParams.setOnClickListener(this);
        mSimpleWithoutParamsForResult.setOnClickListener(this);
        mFindFragment.setOnClickListener(this);
        mWebview.setOnClickListener(this);
        mUserService.setOnClickListener(this);
    }

    private void initViews() {
        mSimpleWithoutParams = findViewById(R.id.btn_simple_without_params);
        mWithParams = findViewById(R.id.btn_with_params);
        mSimpleWithoutParamsForResult = findViewById(R.id.btn_simple_without_params_for_result);
        mFindFragment = findViewById(R.id.btn_find_fragment);
        mWebview = findViewById(R.id.btn_webview);
        mUserService = findViewById(R.id.btn_use_service);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_without_params:
                Test1Activity.launch(this);
                break;
            case R.id.btn_simple_without_params_for_result:
                Test2Activity.launch(this);
                break;
            case R.id.btn_with_params:
                TestParcelable testParcelable = new TestParcelable("fanda", "man");
                TestObj testObj = new TestObj("liuhang", "woman");
                List<TestObj> testObjList = new ArrayList<>();
                testObjList.add(testObj);

                Map<String, List<TestObj>> map = new HashMap<>();
                map.put("testMap", testObjList);

                Uri uri = Uri.parse("pitaya://www.wmzx.com/test/Test3Activit");
                Test3Activity test3Activity = (Test3Activity) ARouter.getInstance().build(uri)
                        .withString("teacherName", "fana")
                        .withInt("age", 18)
                        .withParcelable("testPac", testParcelable)
                        .withObject("testObj", testObj)
                        .withObject("testObjList", testObjList)
                        .withObject("testMap", map)
                        .navigation(this, new NavigationCallbackImpl());//有回调的时候，会优先于全局降级，可以单独对跳转做处理


                //Activity实例是拿不到的，不能这样处理，test3Activity为null
                //仅仅这样传递自定义对象会报错，需要对象解析服务来处理 SerializationService
                break;
            case R.id.btn_find_fragment:
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityOptionsCompat compat = ActivityOptionsCompat.
                            makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);

                    ARouter.getInstance()
                            .build("/test/Test4Activity")
                            .withOptionsCompat(compat)
                            .navigation();
                } else {
                    Toast.makeText(this, "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }
//                TestFragment testFragment = TestFragment.getInstance(this);
                //只能拿到实例，是通过反射拿到的，没经过oncreate，所以数据还没注入，拿不到数据
//                Toast.makeText(this, "找到Fragment:" + testFragment.name, Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_webview:
                TestWebviewActivity.launch(this);
                break;
            case R.id.btn_use_service:
                // 1. (推荐)使用依赖注入的方式发现服务,通过注解标注字段,即可使用，无需主动获取
                // Autowired注解中标注name之后，将会使用byName的方式注入对应的字段，不设置name属性
                // 会默认使用byType的方式发现服务(当同一接口有多个实现的时候，必须使用byName的方式发现服务)
//                TestService testService = (TestService) ARouter.getInstance().build("/service/testService").navigation();//by name way
//                TestService testService = ARouter.getInstance().navigation(TestService.class); // by type way
                testService.test("fanda"); //Ioc 注入方式
                break;
        }
    }

    /**
     * des 跳转回调
     *
     * @author 曾凡达
     *         created at 2018/4/9 20:32
     */
    private static class NavigationCallbackImpl implements NavigationCallback {
        @Override
        public void onFound(Postcard postcard) {
            Log.e("fanda", "找到Test3Activity！");
        }

        @Override
        public void onLost(Postcard postcard) {
            Log.e("fanda", "找不到Test3Activity！");
        }

        @Override
        public void onArrival(Postcard postcard) {

            Log.e("fanda", "Test3Activity跳转完了！" + "----分组---" + postcard.getGroup());
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            Log.e("fanda", "Test3Activity被拦截了！");
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 666) {
            switch (resultCode) {
                case 999:
                    if (data != null) {
                        String result = data.getStringExtra("data");
                        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }
}
