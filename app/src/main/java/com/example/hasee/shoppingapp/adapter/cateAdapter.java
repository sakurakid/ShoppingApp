package com.example.hasee.shoppingapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.bean.CategoryBean;
import com.example.hasee.shoppingapp.widget.GridViewForScrollView;

import java.util.List;

public class cateAdapter extends BaseAdapter {

    private Context context;
    private List<CategoryBean.DataBean> foodDatas;

    public cateAdapter(Context context, List<CategoryBean.DataBean> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }

    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryBean.DataBean dataBean = foodDatas.get(position);
        List<CategoryBean.DataBean.DataListBean> dataList = dataBean.getDataList();
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        cateItemAdapter adapter = new cateItemAdapter(context, dataList);
        viewHold.blank.setText(dataBean.getModuleTitle());
        viewHold.gridView.setAdapter(adapter);
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView blank;
    }

}

