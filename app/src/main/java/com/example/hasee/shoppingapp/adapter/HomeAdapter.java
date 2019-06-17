package com.example.hasee.shoppingapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.widget.UPMarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<String> marqueeList;

    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private static final int BANNER = 0;
    private static final int COLUMN = 1;
    private static final int MARQUEE = 2;
    private static final int NUM_TWO = 3;
    private static final int TITLE = 4;
    private static final int NUM_THREE = 5;
    private static final int NORMAL = 6;

    public HomeAdapter(Context mContext) {
        inflater = LayoutInflater.from(mContext);
        //添加轮播数据
        addBannerData();
        //添加跑马灯数据
        addMarqueeData();
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position >= 1 && position <= 8) {
            return COLUMN;
        } else if (position == 9) {
            return MARQUEE;
        } else if (position >= 10 && position <= 13) {
            return NUM_TWO;
        } else if (position == 14 || position == 18) {
            return TITLE;
        } else if (position >= 15 && position <= 17) {
            return NUM_THREE;
        } else if (position >= 19 && position <= 24) {
            return NORMAL;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                View itemBanner = inflater.inflate(R.layout.item_banner, parent, false);
                return new BannerHolder(itemBanner);
            case COLUMN:
                View itemColumn = inflater.inflate(R.layout.item_column, parent, false);
                return new ColumnHolder(itemColumn);
            case MARQUEE:
                View itemMarquee = inflater.inflate(R.layout.item_marquee, parent, false);
                return new MarqueeHolder(itemMarquee);
            case NUM_TWO:
                View itemNumTwo = inflater.inflate(R.layout.item_num_two, parent, false);
                return new NumTwoHolder(itemNumTwo);
            case TITLE:
                View itemTitle = inflater.inflate(R.layout.item_title, parent, false);
                return new TitleHolder(itemTitle);
            case NUM_THREE:
                View itemNumThree = inflater.inflate(R.layout.item_num_three, parent, false);
                return new NumThreeHolder(itemNumThree);
            case NORMAL:
                View itemNormal = inflater.inflate(R.layout.item_normal, parent, false);
                return new NormalHolder(itemNormal);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            setBanner((BannerHolder) holder);
        } else if (holder instanceof ColumnHolder) {

        } else if (holder instanceof MarqueeHolder && marqueeList != null) {
            setMarquee((MarqueeHolder) holder);
        } else if (holder instanceof NumTwoHolder) {

        } else if (holder instanceof TitleHolder) {
            setTitle((TitleHolder) holder, position);
        } else if (holder instanceof NumThreeHolder) {

        } else if (holder instanceof NormalHolder) {

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case BANNER:
                            return 12;
                        case COLUMN:
                            return 3;
                        case MARQUEE:
                            return 12;
                        case NUM_TWO:
                            return 6;
                        case TITLE:
                            return 12;
                        case NUM_THREE:
                            return 4;
                        case NORMAL:
                            return 6;
                        default:
                            return 12;
                    }
                }
            });
        }
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;

        BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ColumnHolder extends RecyclerView.ViewHolder {

        public ColumnHolder(View itemView) {
            super(itemView);
        }
    }

    public class MarqueeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.marquee)
        UPMarqueeView marquee;

        public MarqueeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class NumTwoHolder extends RecyclerView.ViewHolder {

        public NumTwoHolder(View itemView) {
            super(itemView);
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class NumThreeHolder extends RecyclerView.ViewHolder {

        public NumThreeHolder(View itemView) {
            super(itemView);
        }
    }


    public class NormalHolder extends RecyclerView.ViewHolder {

        public NormalHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 轮播图图片列表
     */
    private void addBannerData() {
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
    }

    /**
     * 添加跑马灯数据
     */
    private void addMarqueeData() {
        marqueeList = new ArrayList<>();
        marqueeList.add("太疯狂！IPhone X首批1分钟卖光。");
        marqueeList.add("家人给2岁孩子喝这个，孩子智力倒退10岁!");
        marqueeList.add("自助餐里面的潜规则，想要吃回本其实很简单。");
        marqueeList.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡。");
    }

    /**
     * 绑定轮播图数据
     */
    private void setBanner(BannerHolder holder) {
        holder.banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        holder.banner.setImageLoader(new GlideImageLoade());
        holder.banner.setImages(list_path);
        holder.banner.setBannerTitles(list_title);
        holder.banner.setBannerAnimation(Transformer.Default);
        holder.banner.isAutoPlay(true);
        holder.banner.setDelayTime(3000);
        holder.banner.setIndicatorGravity(BannerConfig.CENTER);
        holder.banner.start();
    }

    /**
     * 设置跑马灯
     */
    private void setMarquee(MarqueeHolder holder) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < marqueeList.size(); i = i + 2) {
            LinearLayout view = (LinearLayout) inflater.inflate(R.layout.marquee_text, null);
            TextView textTop = (TextView) view.findViewById(R.id.text_top);
            TextView textBottom = (TextView) view.findViewById(R.id.text_bottom);
            textTop.setText(marqueeList.get(i));
            if (marqueeList.size() > i + 1) {
                textBottom.setText(marqueeList.get(i + 1));
            }
            views.add(view);
        }
        holder.marquee.setViews(views);
    }

    /**
     * 模块标题
     */
    private void setTitle(TitleHolder holder, int position) {
        switch (position) {
            case 14:
                holder.tvTitle.setText("精品推荐");
                break;
            case 18:
                holder.tvTitle.setText("猜你喜欢");
                break;
        }
    }
    //自定义的图片加载器
    private class GlideImageLoade extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
        }
    }
}
