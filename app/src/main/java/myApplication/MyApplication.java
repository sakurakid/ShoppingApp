package myApplication;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.example.hasee.shoppingapp.bean.User;
import com.example.hasee.shoppingapp.tool.UserLocalData;

public class MyApplication extends Application{

    public static final int START_FOR_RESULT  = 0 ;
    public static final int START_NO_RESULT  = 1 ;

    private User user ;
    private String token ;
    private static Intent intent ;
    private static  int startIntentStype ;

    public static int getStartIntentStype() {
        return startIntentStype;
    }

    public static void setStartIntentStype(int startIntentStype) {
        MyApplication.startIntentStype = startIntentStype;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public static MyApplication myApplication ;

    public static MyApplication getInstance(){
        return myApplication ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.myApplication = this ;
        initUser();
//        Fresco.initialize(this);
//        NineGridView.setImageLoader(new PicassoImageLoader());

    }

    public void initUser(){
        this.user = UserLocalData.getUser(this );
        this.token = UserLocalData.getToken(this) ;
    }

    public User getUser(){
        return user ;
    }

    public String getToken(){
        return token ;
    }

    public void putUser(User user , String token){

        this.token = token ;
        this.user = user ;
        UserLocalData.putUser(this , user);
        UserLocalData.putToken(this , token);
    }

    public void clearUser(){
        this.user = null ;
        this.token = null ;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);
    }

    public static void jumpToTargetoActivity(Activity activity){
        activity.startActivity(intent);
        intent = null ;
    }

}
