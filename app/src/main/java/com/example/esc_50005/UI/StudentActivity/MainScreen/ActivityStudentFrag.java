package com.example.esc_50005.UI.StudentActivity.MainScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esc_50005.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityStudentFrag extends Fragment {


    public ActivityStudentFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.student_activity_frag, container, false);
    }

}
