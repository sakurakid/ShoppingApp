package com.example.hasee.shoppingapp.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.base.BaseActivity;
import com.example.hasee.shoppingapp.widget.CnToolbar;
import com.example.hasee.shoppingapp.widget.MyEditText;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class Register2Activity extends BaseActivity {

    @BindView(R.id.phone)
    TextView phone ;
    @BindView(R.id.toolBar)
    CnToolbar cnToolbar ;
    @BindView(R.id.identifyCode)
    MyEditText identifyCode ;
    @BindView(R.id.reRequset)
    Button getIdentifyButton ;
    private int CountDownTime = 60 ;
    private Timer timer ;
    private Handler mHandler ;
   // private SMSEvenHandler smsEvenHandler ;
    private String countryCode ;
    private String phoneNum ;
    private String passWord ;
    private String showPhoneNum ;
   // private static OkhttpHelper okhttpHelper;
    private Dialog dialog ;

    private static final int HANDLER_CODE = 0X123 ;
    private static final int COUNTDOWN_MAX_VALUE = 60 ;

    @Override
    protected int getContentViewId() {
        return R.layout.mine_register2_activity;
    }

    @Override
    protected void initView() {

       // okhttpHelper = OkhttpHelper.getOkhttpHelper() ;
        dialog = new Dialog(this) ;

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if( HANDLER_CODE == msg.what ){
                    if(CountDownTime != 0) {
                        CountDownTime-- ;
                        getIdentifyButton.setText(CountDownTime + "秒后可重新发送");
                    }else{
                        getIdentifyButton.setText("重新发送");
                        stopCountDown() ;
                    }
                }
            }
        };

      //  smsEvenHandler = new SMSEvenHandler() ;
     //   SMSSDK.registerEventHandler(smsEvenHandler);

        Intent intent = getIntent() ;
        countryCode = intent.getStringExtra(RegisterActivity.COUNTRY_CODE) ;
        passWord = intent.getStringExtra(RegisterActivity.PASSWORD) ;
        phoneNum = intent.getStringExtra(RegisterActivity.PHONE) ;
        showPhoneNum = cutApartPhoneNum(phoneNum) ;
        phone.setText(showPhoneNum);
//
        beginCountDown() ;
    }
    private void beginCountDown(){
        CountDownTime = COUNTDOWN_MAX_VALUE ;
        timer = new Timer() ;
        getIdentifyButton.setClickable(false);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(HANDLER_CODE) ;
            }
        },0 ,1000);
    }

    private void stopCountDown(){
        timer.cancel();
        getIdentifyButton.setClickable(true);
    }

    private String cutApartPhoneNum(String phone){
        if(null == phone){
            return " " ;
        }
        return phone.substring(0 , 3) + " " + phone.substring(3 ,6) + " " + phone.substring(6) ;

    }

    protected void addListener(){

        cnToolbar.setLeftButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getIdentifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reRequestIdentifyCode() ;
                beginCountDown();
            }
        });

        cnToolbar.setRightButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finishReg() ;
            }
        });
    }

    @Override
    protected void beforLayout() {

    }

    private void finishReg(){

        if(null == getCode()){
            Toast.makeText(this , "请填写验证码" , Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private String getCode(){
        return identifyCode.getText().toString().trim() ;
    }

    private void reRequestIdentifyCode(){
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void afterVerificationCodeRequested(boolean smart) {
        if(smart){
            Toast.makeText(this , "获取验证码成功" , Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this , "获取验证码失败" , Toast.LENGTH_SHORT).show();
        }

    }


    private void closeKeyMode(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(identifyCode.getWindowToken(),0);
    }

    private void showErrorMsg(){
        closeKeyMode();
        Toast.makeText(this, "注册失败" , Toast.LENGTH_LONG).show();
    }
}
