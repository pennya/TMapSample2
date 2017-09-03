package com.example.kjh.tmapsample2.autosearch;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.kjh.tmapsample2.R;
import com.skp.Tmap.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJH on 2017-09-06.
 */

public class AutoCompleteAdapter extends ArrayAdapter<AutoCompleteItem> {

    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<AutoCompleteItem> mSuggestions;

    public AutoCompleteAdapter(@NonNull Activity activity, @LayoutRes int resource) {
        super(activity, resource);
        this.mActivity = activity;
        mLayoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSuggestions = new ArrayList<AutoCompleteItem>();
    }

    @Override
    public int getCount() {
        return mSuggestions.size();
    }

    @Nullable
    @Override
    public AutoCompleteItem getItem(int position) {
        return mSuggestions.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        final AutoCompleteItem i = mSuggestions.get(position);
        if (i != null) {
            AutoCompleteItem item = (AutoCompleteItem)i;
            v = mLayoutInflater.inflate(R.layout.item, null);
            final TextView txtView = (TextView)v.findViewById(R.id.search_title);
            if(txtView != null){
                txtView.setText(item.getTitle());
            }
        }
        return v;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence != null){
                    AutoCompleteParse acp = new AutoCompleteParse();
                    List<AutoCompleteItem> new_suggestions
                            = acp.getAutoComplete(mActivity, charSequence.toString());
                    mSuggestions.clear();
                    for(int i = 0; i< new_suggestions.size(); i++) {
                        mSuggestions.add(new_suggestions.get(i));
                    }

                    filterResults.values = mSuggestions;
                    filterResults.count = mSuggestions.size();
                }


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
