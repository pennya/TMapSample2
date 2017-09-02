package com.example.kjh.tmapsample2.tmap.presenter;

import android.app.Activity;

import com.example.kjh.tmapsample2.tmap.model.ITMapModelCallback;
import com.example.kjh.tmapsample2.tmap.model.TMapModel;
import com.skp.Tmap.TMapView;

/**
 * Created by KJH on 2017-09-03.
 */

public class TMapPresenterImpl implements ITMapPresenter, ITMapModelCallback {

    private TMapPresenterImpl.View view;

    private TMapModel model;

    private Activity activity;

    public TMapPresenterImpl(Activity activity, View view) {
        this.activity = activity;
        this.view = view;

        model = new TMapModel(activity, this);
    }

    @Override
    public void initMap() {
        model.initTMap();
    }

    @Override
    public void findPoiBetweenTwoPoints(double startLongitude, double startLatitude,
                                        double endLongitude, double endLatitude,
                                        String categoryName) {

        model.findPoiBetweenTwoPoints(startLongitude, startLatitude,
                endLongitude, endLatitude, categoryName);

    }

    @Override
    public void getMap(TMapView map) {
        view.setMap(map);
        view.showToast("TMapInit SUCCESS");
    }


}
