package com.example.cindy.esc_50005.UI.ProfSession.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hoonqt on 27/3/18.
 */

public class QnListAdapter extends RecyclerView.Adapter<QnListAdapter.QnViewHolder>{

    private static int viewHolderCount = 0;
    private Context context;

    public QnListAdapter() {

    }

    @Override
    public QnListAdapter.QnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(QnListAdapter.QnViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class QnViewHolder extends RecyclerView.ViewHolder {


        public QnViewHolder(View v) {
            super(v);

        }

        public void bind(int position) {

        }

    }
}
