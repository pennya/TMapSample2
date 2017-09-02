package com.example.kjh.tmapsample2.tmap.presenter;

import com.skp.Tmap.TMapView;

/**
 * Created by KJH on 2017-09-03.
 */

public interface ITMapPresenter {

    void initMap();

    void findPoiBetweenTwoPoints(double startLongitude, double startLatitude,
                                 double endLongitude, double endLatitude, String categoryName);

    interface View {

        void setMap(TMapView map);

        void showToast(String msg);

    }
}
