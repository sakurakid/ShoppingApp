package com.example.hasee.shoppingapp.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.adapter.HomeAdapter;
import com.example.hasee.shoppingapp.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页的fragment
 */
public class HomeFragment extends BaseFragment implements OnBannerListener{
    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private Unbinder mUnBinder;
    @BindView(R.id.rc_home)
    RecyclerView rcHome;



    @Override
    protected int getResRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        //initBanner();
        mUnBinder = ButterKnife.bind(this,mView);
        initView();
    }

    private void initView() {
        HomeAdapter adapter = new HomeAdapter(mView.getContext());
        GridLayoutManager layoutManger = new GridLayoutManager(mView.getContext(), 12);
        rcHome.setLayoutManager(layoutManger);
        rcHome.setAdapter(adapter);
    }

    private void initBanner(){
        banner = (Banner)mView.findViewById(R.id.banner);
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        list_path.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2484903838,2725799446&fm=26&gp=0.jpg");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560429076256&di=4b3ccacfa6cee8ed38a606cb00cdbfbc&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c55e56fdd6de6ac72579484479c7.jpg");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560428850249&di=e85871874e2dd2ea559327b1de5c1bb4&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c8c059367d1aa8012193a376c022.jpg%401280w_1l_2o_100sh.jpg");
        list_title.add("狂欢打折");
        list_title.add("美食不将就");
        list_title.add("大促销");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mUnBinder.unbind();
    }



    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");

    }
    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            try {
                Glide.with(mView.getContext()).load(new URL((String)path)).into(imageView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }


}



