package com.dhw.propertyanimation.model;

import android.os.Handler;
import com.dhw.propertyanimation.bean.GoodsBean;
import com.dhw.propertyanimation.okhttp.GsonObjectCallback;
import com.dhw.propertyanimation.okhttp.OkHttp3Utils;
import com.dhw.propertyanimation.view.GoodsContract;
import java.io.IOException;

public class GoodsModel implements GoodsContract.IGoodModel {
    private String url = "http://120.27.23.105/product/getProducts?pscid=1";
    private Handler handler = new Handler();


    @Override
    public void getDataByNet(final GoodsContract.IGoodView view) {
        OkHttp3Utils.doGet(url, new GsonObjectCallback<GoodsBean>() {
            @Override
            public void onUi(GoodsBean goodsBean) {
                view.setData(goodsBean.data);
            }

            @Override
            public void onFailed(okhttp3.Call call, IOException e) {

            }
        });
    }
}
