package com.example.hasee.shoppingapp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hasee.shoppingapp.activity.LoginActivity;
import com.example.hasee.shoppingapp.contents.Contents;

import butterknife.ButterKnife;
import myApplication.MyApplication;

public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    protected Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在加载布局之前执行一些方法
        beforLayout();
        this.mContext = this;
        //加载布局
        setContentView(getContentViewId());

        //定义一个handle
        mHandler = new Handler();

        //绑定ButterKnife
        ButterKnife.bind(mContext);

        initView();
        addListener();
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void addListener();

    protected abstract void beforLayout();

    protected void startActivityWithLogin(Intent intent , boolean isNeedLogin , int startIntentStype){

        if (isNeedLogin){

            if(MyApplication.getInstance().getUser() == null){

                Intent intent1 = new Intent(this , LoginActivity.class) ;

                if (MyApplication.START_FOR_RESULT == startIntentStype){
                    startActivityForResult(intent1 , Contents.REQUEST_CODE);
                }else if(MyApplication.START_NO_RESULT == startIntentStype){
                    MyApplication.getInstance().setIntent(intent);
                    startActivity(intent1);
                }

            }else {
                this.startActivity(intent);
            }
        }else {
            this.startActivity(intent);
        }
    }

}
