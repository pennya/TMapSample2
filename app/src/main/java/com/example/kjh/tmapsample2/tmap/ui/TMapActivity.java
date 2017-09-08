package com.example.kjh.tmapsample2.tmap.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kjh.tmapsample2.R;
import com.example.kjh.tmapsample2.tmap.presenter.ITMapPresenter;
import com.example.kjh.tmapsample2.tmap.presenter.TMapPresenterImpl;
import com.skp.Tmap.TMapView;

public class TMapActivity extends AppCompatActivity implements ITMapPresenter.View{

    private RelativeLayout mRelativeLayout;
    private ITMapPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmap);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TMap destinations");

        mRelativeLayout = (RelativeLayout)findViewById(R.id.map_view);
        mPresenter = new TMapPresenterImpl(this, this);
        mPresenter.initMap();
        mPresenter.findPoiBetweenTwoPoints(127.001703, 37.539407,
                126.994343, 37.534473, "편의점");
    }

    @Override
    public void setMap(TMapView map) {
        mRelativeLayout.removeAllViews();
        mRelativeLayout.addView(map);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
