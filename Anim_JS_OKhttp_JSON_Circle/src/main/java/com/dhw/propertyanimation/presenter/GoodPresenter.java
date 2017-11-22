package com.dhw.propertyanimation.presenter;

import com.dhw.propertyanimation.model.GoodsModel;
import com.dhw.propertyanimation.view.GoodsContract;

public class GoodPresenter implements GoodsContract.IGoodPresenter {
    private GoodsContract.IGoodView view;
    private GoodsModel model;


    public GoodPresenter(GoodsContract.IGoodView view) {
        this.view = view;
        model = new GoodsModel();
    }

    @Override
    public void showData() {
        model.getDataByNet(view);
    }
}
