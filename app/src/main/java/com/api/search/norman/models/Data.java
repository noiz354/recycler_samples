package com.api.search.norman.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 02/10/2015.
 */
public class Data {
    List<InnerData> datas;

    public Data(List<InnerData> datas) {
        this.datas = datas;
    }

    public Data(){
        datas = new ArrayList<>();
    }

    public List<InnerData> getDatas() {
        return datas;
    }

    public void setDatas(List<InnerData> datas) {
        this.datas = datas;
    }

    public static final class InnerData{
        int id;
        String name;
        String uri;
        String imageUri;
        String price;
        Shop shop;
        WholesalePrice wholesalePrice;

        public InnerData(int id, String name, String uri, String imageUri, String price, Shop shop, WholesalePrice wholesalePrice) {
            this.id = id;
            this.name = name;
            this.uri = uri;
            this.imageUri = imageUri;
            this.price = price;
            this.shop = shop;
            this.wholesalePrice = wholesalePrice;
        }

        public InnerData(){}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getImageUri() {
            return imageUri;
        }

        public void setImageUri(String imageUri) {
            this.imageUri = imageUri;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Shop getShop() {
            return shop;
        }

        public void setShop(Shop shop) {
            this.shop = shop;
        }

        public WholesalePrice getWholesalePrice() {
            return wholesalePrice;
        }

        public void setWholesalePrice(WholesalePrice wholesalePrice) {
            this.wholesalePrice = wholesalePrice;
        }
    }

}
