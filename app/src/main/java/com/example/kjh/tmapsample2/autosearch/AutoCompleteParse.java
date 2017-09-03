package com.example.kjh.tmapsample2.autosearch;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;

import com.example.kjh.tmapsample2.tmap.model.TMapModel;
import com.example.kjh.tmapsample2.tmap.model.TMapWrapper;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJH on 2017-09-06.
 */

public class AutoCompleteParse implements TMapData.FindTitlePOIListenerCallback
{
    Activity mActivity;
    List<AutoCompleteItem> mListData;

    public AutoCompleteParse() {
        mListData = new ArrayList<AutoCompleteItem>();
    }

    public List<AutoCompleteItem> getAutoComplete(Activity activty, String word){
        this.mActivity = activty;

        mListData.clear();

        TMapWrapper tMapWrapper = new TMapWrapper(mActivity);
        tMapWrapper.initTMapView(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });

        tMapWrapper.findTitlePoi(word, 200, this);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return mListData;
    }


    @Override
    public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList) {
        if (arrayList != null) {
            for(int i = 0; i < arrayList.size(); i++) {
                mListData.add(new AutoCompleteItem(arrayList.get(i).getPOIName(),
                        arrayList.get(i).getPOIPoint().getLatitude(),
                        arrayList.get(i).getPOIPoint().getLongitude()));
            }


        }
    }
}
