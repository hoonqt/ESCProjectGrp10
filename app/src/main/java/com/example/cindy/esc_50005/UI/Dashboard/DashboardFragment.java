package com.example.cindy.esc_50005.UI.Dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Login.LoginContract;
import com.example.cindy.esc_50005.UI.Login.LoginPresenter;
import com.example.cindy.esc_50005.UI.Session.SessionActivity;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements DashboardContract.View {

    // UI references.
    SharedPreferences sharedPreferences;
    private DashboardContract.Presenter mPresenter= new DashboardPresenter(this);

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(@NonNull DashboardContract.Presenter presenter) {
        Log.i("checkIfNull","checkIfNull");
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.dashboard_fragment, container, false);
        return view;
    }

    public void attemptLoadCourses()
    {
        mPresenter.loadCoursesFromDatabase();
    }


    public void showSuccessfullyLoadedCourses()
    {
        Log.i("showSuccessfulLogin","showSuccessfulLogin");
        Intent intent = new Intent(getActivity(), SessionActivity.class);
        startActivity(intent);
        //haven't written code that opens the dashboard if login is successful
    }

    public void showUnsuccessfullyLoadedCourses()
    {
        //haven't written code that opens the dashboard if login is successful
    }



    public void showLoadedCourses() {

    }
}





