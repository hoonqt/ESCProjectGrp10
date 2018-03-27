package com.example.cindy.esc_50005.UI.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Otter on 26/3/2018.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(int position) {
        mCurrentPosition = position;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}