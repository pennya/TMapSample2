package com.example.kjh.tmapsample2.autosearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.kjh.tmapsample2.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AutoCompleteTextView getInput = (AutoCompleteTextView) findViewById(R.id.translate_word);
        getInput.setThreshold(1);
        getInput.setAdapter(new  AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));

    }
}
