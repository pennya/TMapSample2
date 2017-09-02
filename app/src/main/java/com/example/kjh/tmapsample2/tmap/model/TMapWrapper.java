package com.example.kjh.tmapsample2.tmap.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.kjh.tmapsample2.R;
import com.skp.Tmap.TMapCircle;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

/**
 * Created by KJH on 2017-09-03.
 */

public class TMapWrapper implements TMapData.FindAroundNamePOIListenerCallback{

    private Activity activity;

    private TMapView mTMapView;

    private final int MARKER_IMAGE = R.drawable.ic_room_black_24dp;

    public TMapView getMapView() {
        return mTMapView;
    }

    public TMapWrapper(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onFindAroundNamePOI(ArrayList<TMapPOIItem> arrayList) {
        addPoiItems(arrayList);
    }


    public void initTMapView() {
        mTMapView = new TMapView(activity);
        mTMapView.setSKPMapApiKey("7d54b976-ee11-3f11-a5d8-0846567726ef");
        mTMapView.setZoomLevel(15);
        mTMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        mTMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        mTMapView.setTrackingMode(true);
    }


    public void findPoiBetweenTwoPoints(
        TMapPoint startPoint,
        TMapPoint endPoint,
        String categoryName) {

        TMapPoint centerPoint = getCenterPoint(startPoint, endPoint);
        double distance = getDistanceBetweenTwoPoints(startPoint, endPoint);

        //check
        addCircle(centerPoint, distance);

        distance /= 2;
        int radius = (int)(distance / 300);

        TMapData tmapData = new TMapData();
        tmapData.findAroundNamePOI(centerPoint, categoryName, radius, 50, this);



    }


    public void findPoiBetweenTwoPoints(
            double startLongitude,
            double startLatitude,
            double endLongitude,
            double endLatitude,
            String categoryName) {

        findPoiBetweenTwoPoints(
                new TMapPoint(startLatitude, startLongitude),
                new TMapPoint(endLatitude, endLongitude),
                categoryName);
    }


    public void addPoiItems(ArrayList<TMapPOIItem> poiItems) {
        for(TMapPOIItem poiItem : poiItems) {
            addPoiItem(poiItem.getPOIName(), poiItem.getPOIPoint());
        }
    }


    private void addPoiItem(String title, TMapPoint point) {

        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), MARKER_IMAGE);

        TMapMarkerItem item = new TMapMarkerItem();
        item.setTMapPoint(point);
        item.setName(title);
        item.setVisible(item.VISIBLE);
        item.setIcon(bitmap);

        String strId = String.format("marker_%s_%d", title, System.currentTimeMillis());
        mTMapView.addMarkerItem(strId, item);
    }


    private void addPoiItem(String title, double Longitude, double Latitude) {

        TMapPoint point = new TMapPoint(Latitude, Longitude);
        addPoiItem(title, point);

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
