package com.example.cindy.esc_50005.UI.ProfSession;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ProfQnAdapter extends RecyclerView.Adapter<ProfQnAdapter.QnViewHolder> {

    private ArrayList dataset;
    private static int viewHolderCount = 0;

    public ProfQnAdapter() {

    }

    @Override
    public ProfQnAdapter.QnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.profqn_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QnViewHolder qnViewHolder = new QnViewHolder(view);
        viewHolderCount++;

        return qnViewHolder;
    }

    @Override
    public void onBindViewHolder(ProfQnAdapter.QnViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        if (dataset == null) {
            return 0;
        }

        else return dataset.size();
    }

    class QnViewHolder extends RecyclerView.ViewHolder {

        public TextView stuff;

        public QnViewHolder(View v) {
            super(v);

        }

        public void bind(int position) {


        }


    }
}
