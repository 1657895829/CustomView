package com.dhw.propertyanimation.view;

import com.dhw.propertyanimation.bean.GoodsBean;

import java.util.List;

public class GoodsContract {

    public interface IGoodView{
        void setData(List<GoodsBean.DataBean> data);
    }

    public interface IGoodModel{
        void getDataByNet(IGoodView view);
    }

    public interface IGoodPresenter{
        void showData();
    }
}
