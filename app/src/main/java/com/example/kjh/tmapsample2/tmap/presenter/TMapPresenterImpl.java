package com.example.kjh.tmapsample2.tmap.presenter;

import android.app.Activity;

import com.example.kjh.tmapsample2.tmap.model.ITMapModelCallback;
import com.example.kjh.tmapsample2.tmap.model.TMapModel;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapView;

/**
 * Created by KJH on 2017-09-03.
 */

public class TMapPresenterImpl implements ITMapPresenter, ITMapModelCallback {

    private TMapPresenterImpl.View mView;
    private TMapModel mModel;
    private Activity mActivity;

    public TMapPresenterImpl(Activity activity, View view) {
        this.mActivity = activity;
        this.mView = view;

        mModel = new TMapModel(mActivity, this);
    }

    @Override
    public void initMap() {
        mModel.initTMap();
    }

    @Override
    public void findPoiBetweenTwoPoints(double startLongitude, double startLatitude,
                                        double endLongitude, double endLatitude,
                                        String categoryName) {

        mModel.findPoiBetweenTwoPoints(startLongitude, startLatitude,
                endLongitude, endLatitude, categoryName);

    }

    @Override
    public void getMap(TMapView map) {
        mView.setMap(map);
        mView.showToast("TMapInit SUCCESS");
    }

    @Override
    public void getTMapMarkerItem(TMapMarkerItem item) {
        StringBuilder info = new StringBuilder();
        info.append(item.getID() + "\n");
        info.append(item.getName() + "\n");
        mView.showToast(info.toString());
    }


}
