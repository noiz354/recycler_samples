package com.api.search.norman.models;

/**
 * Created by admin on 02/10/2015.
 */
public class Shop {
    public static final String TAG = Shop.class.getSimpleName();
    int id;
    int is_gold;
    String name;
    Rating rating;
    String location;
    String reputationImageUri;
    String shopLucky;
    String uri;

    public Shop(int id, int is_gold, String name, Rating rating, String location, String reputationImageUri, String shopLucky) {
        this.id = id;
        this.is_gold = is_gold;
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.reputationImageUri = reputationImageUri;
        this.shopLucky = shopLucky;
    }

    public Shop(){}

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getShopLucky() {
        return shopLucky;
    }

    public void setShopLucky(String shopLucky) {
        this.shopLucky = shopLucky;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_gold() {
        return is_gold;
    }

    public void setIs_gold(int is_gold) {
        this.is_gold = is_gold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReputationImageUri() {
        return reputationImageUri;
    }

    public void setReputationImageUri(String reputationImageUri) {
        this.reputationImageUri = reputationImageUri;
    }
}
