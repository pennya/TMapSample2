package com.example.kjh.tmapsample2.tmap.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kjh.tmapsample2.R;
import com.example.kjh.tmapsample2.tmap.presenter.ITMapPresenter;
import com.example.kjh.tmapsample2.tmap.presenter.TMapPresenterImpl;
import com.skp.Tmap.TMapView;

/**
 * Created by KJH on 2017-09-02.
 */

public class TMapFragment extends Fragment implements ITMapPresenter.View {

    private RelativeLayout relativeLayout;

    private ITMapPresenter presenter;

    public TMapFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_tmap, container, false);
        relativeLayout = (RelativeLayout)rootView.findViewById(R.id.map_view);

        presenter = new TMapPresenterImpl(getActivity(), this);
        presenter.initMap();
        presenter.findPoiBetweenTwoPoints(127.001703, 37.539407,
                126.994343, 37.534473, "편의점");

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setMap(TMapView map) {
        relativeLayout.removeAllViews();
        relativeLayout.addView(map);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
