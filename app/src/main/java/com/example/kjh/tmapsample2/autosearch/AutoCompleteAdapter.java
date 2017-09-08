package com.example.kjh.tmapsample2.autosearch;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kjh.tmapsample2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Created by KJH on 2017-09-06.
 */

public class AutoCompleteAdapter extends RecyclerView.Adapter<AutoCompleteAdapter.ViewHolder> {

    private Activity mActivty;
    private List<AutoCompleteItem> mSuggestions;

    public AutoCompleteAdapter(Activity activity) {
        mActivty = activity;
        mSuggestions = new ArrayList<AutoCompleteItem>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.search_title);
            address = (TextView)itemView.findViewById(R.id.search_address);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mSuggestions.clear();
        if (charText.length() != 0) {
            try {
                AutoCompleteParse acp = new AutoCompleteParse();
                mSuggestions.addAll(acp.execute(charText).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public AutoCompleteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AutoCompleteAdapter.ViewHolder holder, int position) {
        final int ItemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivty, mSuggestions.get(ItemPosition).getLatitude() + "/" +
                        mSuggestions.get(ItemPosition).getLongitude(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.title.setText(mSuggestions.get(position).getTitle());
        holder.address.setText(mSuggestions.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mSuggestions.size();
    }
}
