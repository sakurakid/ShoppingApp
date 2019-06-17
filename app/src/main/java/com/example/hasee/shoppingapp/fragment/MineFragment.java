package com.example.hasee.shoppingapp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.activity.AddressPickerActivity;
import com.example.hasee.shoppingapp.activity.LoginActivity;
import com.example.hasee.shoppingapp.base.BaseFragment;
import com.example.hasee.shoppingapp.widget.CnToolbar;

import myApplication.MyApplication;

public class MineFragment extends BaseFragment {
    private CnToolbar cnToolbar ;
    private Button loginOut ;
    private TextView my_consignee ;
    private TextView my_favorite ;
    private TextView my_orderList ;

    @Override
    protected int getResRootViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {
        cnToolbar = (CnToolbar) mView.findViewById(R.id.toolBar) ;
        loginOut = (Button) mView.findViewById(R.id.loginOut) ;
        my_consignee = (TextView)mView.findViewById(R.id.my_consignee) ;
        my_favorite = (TextView) mView.findViewById(R.id.my_favorite);
        my_orderList = (TextView) mView.findViewById(R.id.my_list) ;

        cnToolbar.setUserNameText("点击登录");
        cnToolbar.setUserPhotoIcontext(getContext() , R.drawable.default_head);
        loginOut.setVisibility(View.GONE);
        cnToolbar.setUserClickable(true);

        initPbtoolbar();

        my_consignee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , AddressPickerActivity.class) ;
                startActivity(intent);
            }
        });

    }
    private void initPbtoolbar(){

        cnToolbar.setUserPhotoClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , LoginActivity.class) ;
                startActivityWithLogin(intent , true , MyApplication.START_FOR_RESULT);
            }
        });

//        User user = MyApplication.getInstance().getUser() ;
//        showUser(user) ;

    }
}
