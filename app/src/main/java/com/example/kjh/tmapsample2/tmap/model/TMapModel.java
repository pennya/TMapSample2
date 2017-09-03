package com.example.kjh.tmapsample2.tmap.model;

import android.app.Activity;
import android.graphics.PointF;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

/**
 * Created by KJH on 2017-09-03.
 */

public class TMapModel implements TMapView.OnClickListenerCallback,
                                      TMapData.FindAroundNamePOIListenerCallback{

    private ITMapModelCallback mCallback;
    private Activity mActivity;
    private TMapWrapper mTMapWrapper;

    public TMapModel(Activity activity, ITMapModelCallback callback) {
        this.mActivity = activity;
        this.mCallback = callback;

        mTMapWrapper = new TMapWrapper(mActivity);
    }

    public void initTMap() {
        mTMapWrapper.initTMapView(this);
        mCallback.getMap(mTMapWrapper.getMapView());
    }

    public void findPoiBetweenTwoPoints(double startLongitude, double startLatitude,
                                        double endLongitude, double endLatitude,
                                        String categoryName) {

        mTMapWrapper.findPoiBetweenTwoPoints(startLongitude, startLatitude,
                endLongitude, endLatitude, categoryName, this);
    }

    @Override
    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> arrayList) {
        mTMapWrapper.addPoiItems(arrayList);
    }

    @Override
    public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {

        if ( arrayList.size() != 0 ) {

            // POI Click
            mCallback.getTMapMarkerItem(arrayList.get(0));

        } else {

            // not POI click
        }

        return false;
    }

    @Override
    public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
        return false;
    }
}
