package com.fanda.zeng.arouterdemo.testactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fanda.zeng.arouterdemo.R;
import com.fanda.zeng.arouterdemo.base.BaseActivity;
import com.fanda.zeng.arouterdemo.testfragment.TestFragment;

/**
 * Created by 曾凡达 on 2018/4/10.
 * des
 */

@Route(path = "/test/Test4Activity")
public class Test4Activity extends BaseActivity implements View.OnClickListener {

    private Button mFindFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_4);

        mFindFragment = findViewById(R.id.btn_find_fragment);
        mFindFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TestFragment testFragment = TestFragment.getInstance(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, testFragment).commit();
    }
}
