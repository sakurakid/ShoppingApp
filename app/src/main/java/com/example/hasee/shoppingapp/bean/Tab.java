package com.example.hasee.shoppingapp.bean;

/**
 * 导航栏工具类
 */
public class Tab {
    private  int title; //文字
    private  int icon;//图标
    private Class fragment;//绑定的fragment

    public Tab(int title,int icon,Class fragment){
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }
    public Class getFragment() {
        return fragment;
    }

    public int getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
