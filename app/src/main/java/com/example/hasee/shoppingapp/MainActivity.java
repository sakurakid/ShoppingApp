package com.example.hasee.shoppingapp;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.hasee.shoppingapp.base.BaseActivity;
import com.example.hasee.shoppingapp.bean.Tab;
import com.example.hasee.shoppingapp.fragment.HomeFragment;
import com.example.hasee.shoppingapp.fragment.MineFragment;
import com.example.hasee.shoppingapp.fragment.SearchFragment;
import com.example.hasee.shoppingapp.fragment.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private FragmentTabHost mTabhost; //底部导航栏
    private LayoutInflater mInflater;
    private List<Tab> mTabList = new ArrayList<>(4);



    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initTab ();
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void beforLayout() {

    }

    public void initTab (){

        Tab HomeTab = new Tab(R.string.home , R.drawable.selector_icon_home, HomeFragment.class) ;
        Tab SearchTab = new Tab(R.string.catagory , R.drawable.sleector_icon_search, SearchFragment.class) ;
        Tab ShoppingTab = new Tab(R.string.cart , R.drawable.selector_icon_shopping, ShoppingFragment.class) ;
        Tab MineTab = new Tab(R.string.mine , R.drawable.selector_icon_mine, MineFragment.class) ;

        mTabList.add(HomeTab) ;
        mTabList.add(SearchTab) ;
        mTabList.add(ShoppingTab) ;
        mTabList.add(MineTab) ;

        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost)this.findViewById(R.id.tabHost);
        mTabhost.setup(this , getSupportFragmentManager() , R.id.realTabContent);



        //将每一个Tab装入fragmentTabHost
        for(Tab tabIndicator : mTabList){
        TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tabIndicator.getTitle()));
        tabSpec.setIndicator(buildIndicator(tabIndicator));//将view扔进去
        mTabhost.addTab(tabSpec, tabIndicator.getFragment(),null);
        }

        mTabhost.setCurrentTab(0);//默认选择第一个

    }

    private View buildIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView)view.findViewById(R.id.tab_photo);
        TextView text = (TextView) view.findViewById(R.id.tab_title);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
