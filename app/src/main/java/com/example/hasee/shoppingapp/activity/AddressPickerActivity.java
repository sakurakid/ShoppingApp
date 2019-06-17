package com.example.hasee.shoppingapp.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hasee.shoppingapp.R;
import com.example.hasee.shoppingapp.bean.AddressBean;
import com.example.hasee.shoppingapp.widget.AreaPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AddressPickerActivity extends AppCompatActivity {
    private AreaPickerView areaPickerView;
    private List<AddressBean> addressBeans;
    private Button button;
    private int[] i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresspicker);

        Gson gson = new Gson();
        addressBeans = gson.fromJson(getCityJson(), new TypeToken<List<AddressBean>>() {
        }.getType());



        areaPickerView = new AreaPickerView(this, R.style.Dialog, addressBeans);
        areaPickerView.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
            @Override
            public void callback(int... value) {
                i=value;
                if (value.length == 3)
                    button.setText(addressBeans.get(value[0]).getLabel() + "-" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel() + "-" + addressBeans.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getLabel());
                else
                    button.setText(addressBeans.get(value[0]).getLabel() + "-" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel());
            }
        });


    }
    public void btn(View view) {
        button = (Button) view;
        areaPickerView.setSelect(i);
        areaPickerView.show();
    }



    private String getCityJson() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = this.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("region.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}


