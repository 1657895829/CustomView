package com.dhw.propertyanimation.bean;

import com.google.gson.Gson;

import java.util.List;

//GoodBean类
public class GoodsBean {
    public String msg;
    public String code;
    public String page;
    public List<DataBean> data;

    public static GoodsBean objectFromData(String str) {

        return new Gson().fromJson(str, GoodsBean.class);
    }
    public static class DataBean {
        public double bargainPrice;
        public String createtime;
        public String detailUrl;
        public String images;
        public int itemtype;
        public int pid;
        public float price;
        public int pscid;
        public int salenum;
        public int sellerid;
        public String subhead;
        public String title;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}


