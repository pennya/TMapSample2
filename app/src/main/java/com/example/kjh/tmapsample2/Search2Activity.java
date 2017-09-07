package com.example.kjh.tmapsample2;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.kjh.tmapsample2.autosearch.AutoCompleteAdapter;
import com.example.kjh.tmapsample2.autosearch2.ClearEditText;

public class Search2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search destinations");

        AutoCompleteAdapter dataAdapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(dataAdapter);
        listView.setTextFilterEnabled(true);

        ClearEditText clearEditText = (ClearEditText)findViewById(R.id.clear_edit_text);
        clearEditText.setListViewAdpater(dataAdapter);


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
