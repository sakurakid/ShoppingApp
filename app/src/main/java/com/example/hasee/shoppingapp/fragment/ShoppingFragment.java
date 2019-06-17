package com.example.hasee.shoppingapp.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.adapter.ShoppingCartAdapter;
import com.example.hasee.shoppingapp.base.BaseFragment;
import com.example.hasee.shoppingapp.bean.ShoppingCartBean;
import com.example.hasee.shoppingapp.tool.ToastUtil;
import com.example.hasee.shoppingapp.widget.CnToolbar;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends BaseFragment implements View.OnClickListener
        , ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface  {
    //全选
    CheckBox ckAll;
    //总额
    TextView tvShowPrice;
    //结算
    TextView tvSettlement;

    private CnToolbar cnToolbar ;

    ListView list_shopping_cart;
    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    @Override
    protected int getResRootViewId() {
        return  R.layout.fragment_shopping;
    }

    @Override
    protected void init() {
        cnToolbar = (CnToolbar) mView.findViewById(R.id.toolBar) ;
        ckAll= (CheckBox) mView.findViewById(R.id.ck_all);
        tvShowPrice= (TextView)mView.findViewById(R.id.tv_show_price);
        tvSettlement= (TextView) mView.findViewById(R.id.tv_settlement);
        list_shopping_cart= (ListView) mView.findViewById(R.id.list_shopping_cart);

        ckAll.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
        cnToolbar.setRightButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = !flag;
                if (flag) {
                    cnToolbar.setRightButtonText("完成");
                    shoppingCartAdapter.isShow(false);
                } else {
                    cnToolbar.setRightButtonText("编辑");
                    shoppingCartAdapter.isShow(true);
                }
            }

        });

        initData();

    }
    //初始化数据
    protected void initData() {

        for (int i = 0; i < 2; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("上档次的T桖");
            shoppingCartBean.setDressSize(20);
            shoppingCartBean.setId(i);
            shoppingCartBean.setPrice(30.6);
            shoppingCartBean.setCount(1);
            shoppingCartBean.setImageUrl("https://img.alicdn.com/bao/uploaded/i2/TB1YfERKVXXXXanaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
            shoppingCartBeanList.add(shoppingCartBean);
        }
        for (int i = 0; i < 2; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("瑞士正品夜光男女士手表情侣精钢带男表防水石英学生非天王星机械");
            shoppingCartBean.setAttribute("黑白色");
            shoppingCartBean.setPrice(89);
            shoppingCartBean.setId(i+2);
            shoppingCartBean.setCount(3);
            shoppingCartBean.setImageUrl("https://gd1.alicdn.com/imgextra/i1/2160089910/TB2M_NSbB0kpuFjSsppXXcGTXXa_!!2160089910.jpg");
            shoppingCartBeanList.add(shoppingCartBean);
        }
        shoppingCartAdapter = new ShoppingCartAdapter(mView.getContext());
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        list_shopping_cart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
//            case R.id.bt_header_right:
//                flag = !flag;
//                if (flag) {
//                    btnEdit.setText("完成");
//                    shoppingCartAdapter.isShow(false);
//                } else {
//                    btnEdit.setText("编辑");
//                    shoppingCartAdapter.isShow(true);
//                }
//                break;
            case R.id.tv_settlement: //结算
                lementOnder();
                break;

        }

    }


    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
        for (ShoppingCartBean bean:shoppingCartBeanList ){
            boolean choosed = bean.isChoosed();
            if (choosed){
                String shoppingName = bean.getShoppingName();
                int count = bean.getCount();
                double price = bean.getPrice();
                int size = bean.getDressSize();
                String attribute = bean.getAttribute();
                int id = bean.getId();
            }
        }
        ToastUtil.showL(mView.getContext(),"总价："+totalPrice);

        //跳转到支付界面
    }
    /**
     * 单选
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
    /**
     * 遍历list集合
     * @return
     */
    private boolean isAllCheck() {

        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }
    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tvShowPrice.setText("合计:" + totalPrice);
        tvSettlement.setText("结算(" + totalCount + ")");
    }
    /**
     * 增加
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void onStop() {
        super.onStop();
        shoppingCartBeanList.clear();

    }
}

