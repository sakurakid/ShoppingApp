package com.example.hasee.shoppingapp.activity;

import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.base.BaseActivity;
import com.example.hasee.shoppingapp.widget.CnToolbar;
import com.example.hasee.shoppingapp.widget.MyEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.country)
    TextView country ;
    @BindView(R.id.toolBar)
    CnToolbar cnToolbar ;
    @BindView(R.id.countryNum)
    TextView countryNum ;
    @BindView(R.id.phone)
    MyEditText phone ;
    @BindView(R.id.password)
    MyEditText password ;
   // SMSEvenHandler smsEvenHandler ;
    String countryCode ;
    String phoneCode ;
    String passWord ;

    private static final String DEFAUT_COUNTRY_CODE = "42" ;
    public static final String PHONE = "phone" ;
    public static final String PASSWORD = "passWord" ;
    public static final String COUNTRY_CODE = "countryCode" ;

    @Override
    protected int getContentViewId() {
        return R.layout.mine_register1_activity;
    }

    @Override
    protected void initView() {

        phone.setInputType(InputType.TYPE_CLASS_NUMBER); //输入类型
        phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)}); //最大输入长度



    }

    @Override
    protected void addListener(){

        /**
         * 返回上一页
         */
        cnToolbar.setLeftButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 点击下一步，进入服务器获取信息，发送验证码
         */
        cnToolbar.setRightButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (getCode()){
                   JumpToRegister2() ;
               }
            }
        });
    }

    @Override
    protected void beforLayout() {

    }

    private void JumpToRegister2(){
        Intent intent = new Intent(this , Register2Activity.class) ;

        intent.putExtra(PHONE , phoneCode) ;
        intent.putExtra(PASSWORD , passWord) ;
        intent.putExtra(COUNTRY_CODE , countryCode) ;
        Log.d("233","111");
        startActivity(intent);
        Log.d("233","222");
    }

    private boolean getCode(){

        phoneCode = phone.getText().toString().trim() ;
        countryCode = countryNum.getText().toString().trim() ;
        passWord = password.getText().toString().trim()  ;

        if(!requestIdentifyCode(phoneCode , countryCode ,passWord)){
            return false;
        }
        return true ;

    }

    private boolean requestIdentifyCode(String phoneCode , String countryCode ,String passWord ){

        if (countryCode.startsWith("+")){
            countryCode = countryCode.substring(1) ;
        }

        if (TextUtils.isEmpty(phoneCode)){
            Toast.makeText(this , "请输入手机号码" , Toast.LENGTH_SHORT).show();
            return false;
        }

        if (countryCode == "86"){
            if (phoneCode.length() !=11 ){
                Toast.makeText(this , "手机号码长度错误" , Toast.LENGTH_SHORT).show();
                return false ;
            }
        }

        String rule = "^1(3|5|7|8|4)\\d{9}" ;
        Pattern p = Pattern.compile(rule) ;
        Matcher m = p.matcher(phoneCode) ;

        if (!m.matches()){
            Toast.makeText(this , "手机号码格式错误" , Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(passWord) || passWord.length()<6 ){
            Toast.makeText(this , "请填写超过6位即6位以上的密码" , Toast.LENGTH_SHORT).show();
            return false;
        }

        return true ;
    }

    private void afterVerificationCodeRequested(boolean smart) {

        Toast.makeText(this ,smart ? "获取验证码失败" : "获取验证码成功" , Toast.LENGTH_SHORT).show();
        if(!smart){
            JumpToRegister2() ;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
