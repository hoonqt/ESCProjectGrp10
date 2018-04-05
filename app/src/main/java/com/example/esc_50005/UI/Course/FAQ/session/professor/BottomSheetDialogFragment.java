package com.example.esc_50005.UI.Course.FAQ.session.professor;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esc_50005.R;

/**
 * Created by cindy on 5/4/2018.
 */

public class BottomSheetDialogFragment extends AppCompatDialogFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        return view;
    }
}
