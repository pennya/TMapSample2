package com.example.kjh.tmapsample2.tmap.model;

import android.app.Activity;

/**
 * Created by KJH on 2017-09-03.
 */

public class TMapModel {

    private ITMapModelCallback callback;

    private Activity activity;

    private TMapWrapper mTMapWrapper;


    public TMapModel(Activity activity, ITMapModelCallback callback) {
        this.activity = activity;
        this.callback = callback;

        mTMapWrapper = new TMapWrapper(activity);
    }

    public void initTMap() {
        mTMapWrapper.initTMapView();
        callback.getMap(mTMapWrapper.getMapView());
    }

    public void findPoiBetweenTwoPoints(double startLongitude, double startLatitude,
                                        double endLongitude, double endLatitude,
                                        String categoryName) {
        mTMapWrapper.findPoiBetweenTwoPoints(startLongitude, startLatitude,
                endLongitude, endLatitude, categoryName);
    }
}
