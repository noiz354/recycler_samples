package com.api.search.norman.models;

/**
 * Created by m.normansyah on 02/10/2015.
 */
public class Rating {
    int speed, accuracy, service, reputation_score;

    private Rating(int speed, int accuracy, int service, int reputation_score) {
        this.speed = speed;
        this.accuracy = accuracy;
        this.service = service;
        this.reputation_score = reputation_score;
    }

    public Rating(){}

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getReputation_score() {
        return reputation_score;
    }

    public void setReputation_score(int reputation_score) {
        this.reputation_score = reputation_score;
    }
}
