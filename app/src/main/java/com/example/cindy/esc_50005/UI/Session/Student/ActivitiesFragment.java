package com.example.cindy.esc_50005.UI.Session.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cindy.esc_50005.R;

/**
 * Created by 1002215 on 20/2/18.
 */

public class ActivitiesFragment extends android.support.v4.app.Fragment {



    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_progress_main, container, false);
        return view;
    }


}
