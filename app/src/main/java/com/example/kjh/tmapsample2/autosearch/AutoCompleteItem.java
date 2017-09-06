package com.example.kjh.tmapsample2.autosearch;

/**
 * Created by KJH on 2017-09-06.
 */

public class AutoCompleteItem {
    private String title;
    private double latitude;
    private double longitude;

    public AutoCompleteItem(String title, double latitude, double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
