package com.fanda.zeng.arouterdemo.interceptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fanda.zeng.arouterdemo.MainActivity;
import com.fanda.zeng.arouterdemo.MainLooper;

/**
 * Created by 曾凡达 on 2018/4/11.
 * des  拦截器，比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
 * 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
 * 里面的方法不在主线程进行
 */

@Interceptor(priority = 8,name = "测试拦截器")
public class TestInterceptor implements IInterceptor{

    private Context mContext;
    private Postcard mPostcard;

    @Override
    public void process( Postcard postcard, final InterceptorCallback callback) {
        mPostcard = postcard;
        //更改路径，不会使跳转的界面改变 ,只是修改了一些参数，拦截对象，做一些操作，目标不变
        mPostcard.setUri(Uri.parse("pitaya:www.wmzx.com/test/Test1Activity"));
        //注意： onContinue 或 onInterrupt 方法至少调用一个，不然跳转会无效
        if ("/test/Test3Activity".equals(postcard.getPath())) {
            postcard.setUri(Uri.parse("pitaya:www.wmzx.com/test/Test1Activity"));
            final AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.getThis());
            ab.setCancelable(false);
            ab.setTitle("温馨提醒");
            ab.setMessage("想要跳转到Test3Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)");
            ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mPostcard.setUri(Uri.parse("pitaya:www.wmzx.com/test/Test1Activity"));
                    callback.onContinue(mPostcard);
                }
            });
            ab.setNeutralButton("登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    ARouter.getInstance().build("/test/Test1Activity").navigation();
//                    callback.onInterrupt(null);
                }
            });
            ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mPostcard.withString("extra", "我是在拦截器中附加的参数");
                    callback.onContinue(mPostcard);
                }
            });

            MainLooper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ab.create().show();
                }
            });
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init( Context context) {
        mContext = context;
        MainLooper.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "interceptor init", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
