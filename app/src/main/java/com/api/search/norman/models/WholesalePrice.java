package com.api.search.norman.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class WholesalePrice {
    public static final String TAG = WholesalePrice.class.getSimpleName();
    List<Price> mPrices;

    public WholesalePrice(List<Price> mPrices) {
        this.mPrices = mPrices;
    }

    public WholesalePrice(){
        mPrices = new ArrayList<>();
    }

    public List<Price> getPrices() {
        return mPrices;
    }

    public void setPrices(List<Price> mPrices) {
        this.mPrices = mPrices;
    }

    public void addPrice(Price mPrice){
        this.mPrices.add(mPrice);
    }

    public static class Price{
        int count_min, count_max;
        String price;

        public Price(){}

        public Price(int count_min, int count_max, String price) {
            this.count_min = count_min;
            this.count_max = count_max;
            this.price = price;
        }

        public int getCount_min() {
            return count_min;
        }

        public void setCount_min(int count_min) {
            this.count_min = count_min;
        }

        public int getCount_max() {
            return count_max;
        }

        public void setCount_max(int count_max) {
            this.count_max = count_max;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
