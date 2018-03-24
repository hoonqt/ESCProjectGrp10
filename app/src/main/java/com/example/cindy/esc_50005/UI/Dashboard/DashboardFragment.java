package com.example.cindy.esc_50005.UI.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.cindy.esc_50005.UI.Login.LoginContract;
import com.example.cindy.esc_50005.UI.Login.LoginPresenter;
import com.example.cindy.esc_50005.UI.Session.QuestionsAdapter;
import com.example.cindy.esc_50005.UI.Session.QuestionsFragment;
import com.example.cindy.esc_50005.UI.Session.QuestionsItemListener;
import com.example.cindy.esc_50005.UI.Session.SessionActivity;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class DashboardFragment extends Fragment implements DashboardContract.View {

    // UI references.
    SharedPreferences sharedPreferences;
    private DashboardContract.Presenter mPresenter= new DashboardPresenter(this);
    private CoursesAdapter mCoursesAdapter;
    private RecyclerView coursesListRecycler;
    private DashboardFragment.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;

    public DashboardFragment() {
        // Required empty public constructor
    }

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    public void setPresenter(@NonNull DashboardContract.Presenter presenter) {
        Log.i("checkIfNull","checkIfNullDashboard");
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.dashboard_fragment, container, false);
        coursesListRecycler=view.findViewById(R.id.recyclerViewDashboardCourses);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = DashboardFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        coursesListRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        attemptLoadCourses();
        return view;
    }

    public void placeholder()
    {

    }

    public void attemptLoadCourses()
    {
        Log.i("attemptLoad","attemptLoad");
        mPresenter.loadCoursesFromDatabase(getActivity().getApplicationContext());
    }


    public void showSuccessfullyLoadedCourses(ArrayList<String> coursesList)
    {
        Log.i("showSuccessfulLogin","showSuccessfulLogin");
        mCoursesAdapter=new CoursesAdapter(coursesList,mItemListener);
        coursesListRecycler.setAdapter(mCoursesAdapter);
    }

    public void showUnsuccessfullyLoadedCourses()
    {
        //haven't written code that opens the dashboard if login is successful
    }



    public void showLoadedCourses() {

    }
    CoursesItemListener mItemListener = new CoursesItemListener() {
        @Override
        public void moveToCourseScreen(String clickedCourse) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Course Activity", clickedCourse);
            editor.commit();
            Intent intent = new Intent(getActivity(), CourseActivity.class);
            startActivity(intent);
        }
    };
}





