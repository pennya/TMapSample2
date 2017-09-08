package com.example.kjh.tmapsample2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kjh.tmapsample2.autosearch.SearchActivity;
import com.example.kjh.tmapsample2.tmap.ui.TMapActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button autoSearchButton;
    private Button tMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoSearchButton = (Button)findViewById(R.id.auto_search_btn);
        tMapButton = (Button)findViewById(R.id.tmap_btn);

        autoSearchButton.setOnClickListener(this);
        tMapButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.auto_search_btn:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;

            case R.id.tmap_btn:
                intent = new Intent(MainActivity.this, TMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
