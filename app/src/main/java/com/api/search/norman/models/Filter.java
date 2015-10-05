package com.api.search.norman.models;

/**
 * Created by admin on 05/10/2015.
 */
public class Filter {
    String query;

    public Filter(){}

    public Filter(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
