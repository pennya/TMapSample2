package com.example.kjh.tmapsample2.autosearch;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;

import com.example.kjh.tmapsample2.Define;
import com.example.kjh.tmapsample2.autosearch.jsonscheme.Poi;
import com.example.kjh.tmapsample2.autosearch.jsonscheme.SearchPoiInfo;
import com.example.kjh.tmapsample2.autosearch.jsonscheme.TMapSearchInfo;
import com.example.kjh.tmapsample2.tmap.model.TMapModel;
import com.example.kjh.tmapsample2.tmap.model.TMapWrapper;
import com.google.gson.Gson;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJH on 2017-09-06.
 */

public class AutoCompleteParse
{
    private List<AutoCompleteItem> mListData;

    public AutoCompleteParse() {
        mListData = new ArrayList<AutoCompleteItem>();
    }

    public List<AutoCompleteItem> getAutoComplete(String word){

        try{
            URL acUrl = new URL("https://apis.skplanetx.com/tmap/pois?areaLMCode=&centerLon=&centerLat=&count=&page=&reqCoordType=&searchKeyword="
                    + word + "&callback=&areaLLCode=&multiPoint=&searchtypCd=&radius=&searchType=&resCoordType=&version=1"
            );

            URLConnection acConn = acUrl.openConnection();
            acConn.setRequestProperty("Accept", "application/json");
            acConn.setRequestProperty("appKey", Define.TMAP_APP_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    acConn.getInputStream()));

            String line = reader.readLine();
            if(line == null){
                mListData.clear();
                return mListData;
            }

            mListData.clear();
            TMapSearchInfo searchPoiInfo = new Gson().fromJson(line, TMapSearchInfo.class);

            List<Poi> poi =  searchPoiInfo.getSearchPoiInfo().getPois().getPoi();
            for(int i =0; i < poi.size(); i++){
                mListData.add(new AutoCompleteItem(poi.get(i).getName(),
                        Double.parseDouble(poi.get(i).getFrontLat()),
                        Double.parseDouble(poi.get(i).getFrontLon()))
                );
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return mListData;
    }
}
