package com.example.kjh.tmapsample2.autosearch.jsonscheme;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KIM on 2017-09-07.
 */

public class Pois {
    private List<Poi> poi = null;

    public List<Poi> getPoi() {
        return poi;
    }

    public void setPoi(List<Poi> poi) {
        this.poi = poi;
    }

}
