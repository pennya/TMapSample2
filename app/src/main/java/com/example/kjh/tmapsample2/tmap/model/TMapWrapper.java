package com.example.kjh.tmapsample2.tmap.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.kjh.tmapsample2.R;
import com.skp.Tmap.TMapCircle;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapMarkerItem2;
import com.skp.Tmap.TMapMarkerItemLayer;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

/**
 * Created by KJH on 2017-09-03.
 */

public class TMapWrapper{

    private final int TMAP_RADIUS_DEFAULT = 300; // 300m
    private final int MAX_SEARCH_COUNT = 100;
    private final int MARKER_IMAGE = R.drawable.ic_room_black_24dp;
    private final String TMAP_API_KEY = "7d54b976-ee11-3f11-a5d8-0846567726ef";
    private Activity mActivity;
    private TMapView mTMapView;

    public TMapView getMapView() {
        return mTMapView;
    }

    public TMapWrapper(Activity activity) {
        this.mActivity = activity;
    }

    public void initTMapView(TMapView.OnClickListenerCallback listener) {
        mTMapView = new TMapView(mActivity);
        mTMapView.setSKPMapApiKey(TMAP_API_KEY);
        mTMapView.setZoomLevel(15);
        mTMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        mTMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        mTMapView.setTrackingMode(true);
        mTMapView.setOnClickListenerCallBack(listener);
    }

    public void findTitlePoi(String title, int nSearchCount,
                                  TMapData.FindTitlePOIListenerCallback listener){


        TMapData tmapData = new TMapData();
        tmapData.findTitlePOI(title, nSearchCount, listener);

    }

    public void findPoiBetweenTwoPoints(
        TMapPoint startPoint,
        TMapPoint endPoint,
        String categoryName,
        TMapData.FindAroundNamePOIListenerCallback listener) {

        TMapPoint centerPoint = getCenterPoint(startPoint, endPoint);
        int radius = getRadiusBetweenTwoPoints(startPoint, endPoint);

        mTMapView.setCenterPoint(
                centerPoint.getLongitude(),
                centerPoint.getLatitude(),
                true);

        //check
        addCircle(centerPoint, getDistanceBetweenTwoPoints(startPoint, endPoint));

        TMapData tmapData = new TMapData();
        tmapData.findAroundNamePOI(centerPoint, categoryName, radius, MAX_SEARCH_COUNT, listener);
    }

    public void findPoiBetweenTwoPoints(
            double startLongitude,
            double startLatitude,
            double endLongitude,
            double endLatitude,
            String categoryName,
            TMapData.FindAroundNamePOIListenerCallback listener) {

        findPoiBetweenTwoPoints(
                new TMapPoint(startLatitude, startLongitude),
                new TMapPoint(endLatitude, endLongitude),
                categoryName, listener);
    }

    public void addPoiItems(ArrayList<TMapPOIItem> poiItems) {

        // TMapPOIItem add
        //mTMapView.addTMapPOIItem(poiItems);

        // TMapMarkerItem add
        for(TMapPOIItem poiItem : poiItems) {
            addPoiItem(poiItem);
        }
    }

    private void addPoiItem(TMapPOIItem poiItem) {

        Bitmap bitmap = BitmapFactory.decodeResource(mActivity.getResources(), MARKER_IMAGE);

        TMapMarkerItem item = new TMapMarkerItem();
        item.setTMapPoint(poiItem.getPOIPoint());
        item.setName(poiItem.getPOIName());
        item.setVisible(item.VISIBLE);
        item.setIcon(bitmap);

        String strId = String.format("marker_%s_%d",
                                    poiItem.getPOIName(), System.currentTimeMillis());
        mTMapView.addMarkerItem(strId, item);
    }

    private int getRadiusBetweenTwoPoints(TMapPoint startPoint, TMapPoint endPoint) {
        double distance = getDistanceBetweenTwoPoints(startPoint, endPoint);
        distance /= 2;
        return (int)(distance / TMAP_RADIUS_DEFAULT);
    }

    private TMapPoint getCenterPoint(
            double startLongitude,
            double startLatitude,
            double endLongitude,
            double endLatitude) {

        return new TMapPoint(
                (endLatitude + startLatitude) / 2,
                (endLongitude + startLongitude) / 2
        );
    }


    private TMapPoint getCenterPoint(TMapPoint startPoint, TMapPoint endPoint) {
        return new TMapPoint(
                (endPoint.getLatitude() + startPoint.getLatitude()) / 2,
                (endPoint.getLongitude() + startPoint.getLongitude()) / 2
        );
    }


    private double getDistanceBetweenTwoPoints(
            TMapPoint startPoint,
            TMapPoint endPoint ) {

        TMapPolyLine tpolyline = new TMapPolyLine();
        tpolyline.addLinePoint(startPoint);
        tpolyline.addLinePoint(endPoint);

        return tpolyline.getDistance();
    }


    private double getDistanceBetweenTwoPoints(
            double startLongitude,
            double startLatitude,
            double endLongitude,
            double endLatitude) {

        TMapPolyLine tpolyline = new TMapPolyLine();
        tpolyline.addLinePoint(new TMapPoint(startLongitude, startLatitude));
        tpolyline.addLinePoint(new TMapPoint(endLongitude, endLatitude));

        return tpolyline.getDistance();
    }


    private void addCircle(TMapPoint centerPoint, double distance) {
        TMapCircle circle = new TMapCircle();
        circle.setCenterPoint(centerPoint);
        circle.setRadius(distance/2);
        circle.setLineColor(Color.RED);
        circle.setAreaColor(Color.BLUE);
        circle.setRadiusVisible(true);
        circle.setCircleWidth(2);
        circle.setAreaAlpha(30);
        mTMapView.addTMapCircle("circle", circle);
    }
}
