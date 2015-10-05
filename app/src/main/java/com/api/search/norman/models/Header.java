package com.api.search.norman.models;

/**
 * Created by admin on 05/10/2015.
 */
public class Header {
    public static final String TAG = Header.class.getSimpleName();
    int totalData;
    int totalDataNoCategory;

    public Header(int totalData, int totalDataNoCategory) {
        this.totalData = totalData;
        this.totalDataNoCategory = totalDataNoCategory;
    }

    @Override
    public String toString() {
        return "Header{" +
                "totalData=" + totalData +
                ", totalDataNoCategory=" + totalDataNoCategory +
                '}';
    }
}
