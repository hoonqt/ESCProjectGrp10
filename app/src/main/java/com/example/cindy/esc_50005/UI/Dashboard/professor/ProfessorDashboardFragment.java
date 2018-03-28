package com.example.cindy.esc_50005.UI.Dashboard.professor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.cindy.esc_50005.UI.Dashboard.main.CoursesAdapter;
import com.example.cindy.esc_50005.UI.Dashboard.main.CoursesItemListener;
import com.example.cindy.esc_50005.UI.Dashboard.main.DashboardContract;
import com.example.cindy.esc_50005.UI.Dashboard.main.DashboardPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;


public class ProfessorDashboardFragment extends Fragment implements DashboardContract.View, View.OnClickListener {

    // UI references.
    SharedPreferences sharedPreferences;
    private DashboardContract.Presenter mPresenter= new DashboardPresenter(this);
    private CoursesAdapter mCoursesAdapter;
    private RecyclerView coursesListRecycler;
    private ProfessorDashboardFragment.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;

    public ProfessorDashboardFragment() {
    }

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    public void setPresenter(@NonNull DashboardContract.Presenter presenter) {
        Log.i("checkIfNull","checkIfNullDashboard");
        mPresenter = checkNotNull(presenter);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.dashboard_fragment, container, false);
        coursesListRecycler=view.findViewById(R.id.recyclerViewDashboardCourses);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = ProfessorDashboardFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        coursesListRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        button=view.findViewById(R.id.addNewCourse);
        button.setOnClickListener(this);

        attemptLoadCourses();
        return view;
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

    public void addValidNewCourse()
    {
        Log.i("going to load it again","going to load it again");
        attemptLoadCourses();
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

    public void onClick(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getActivity());
        alertDialog.setTitle("Add New Course");
        LinearLayout layout = new LinearLayout(this.getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText courseId = new EditText(getActivity().getApplicationContext());
        courseId.setHint("Course Id");
        courseId.setId(0);
        final EditText courseName = new EditText(getActivity().getApplicationContext());
        courseName.setHint("Course Name");
        alertDialog.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Log.i("start query","start query");
                mPresenter.queryCourseBeforeAdding(Double.parseDouble(courseId.getText().toString()),courseName.getText().toString());
                dialog.cancel();
            }
        });
        layout.addView(courseId);
        layout.addView(courseName);
        alertDialog.setView(layout);
        alertDialog.create();
        alertDialog.show();

    }
    public void showAddInvalidCourse()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getActivity());
        alertDialog.setTitle("Course already exists in the database!");
        alertDialog.create();
        alertDialog.show();
    }

}





