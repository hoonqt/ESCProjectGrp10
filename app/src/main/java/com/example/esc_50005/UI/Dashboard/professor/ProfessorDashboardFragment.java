package com.example.esc_50005.UI.Dashboard.professor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.esc_50005.UI.Dashboard.main.CoursesAdapter;
import com.example.esc_50005.UI.Dashboard.main.CoursesItemListener;
import com.example.esc_50005.UI.Dashboard.main.DashboardContract;
import com.example.esc_50005.UI.Dashboard.main.DashboardPresenter;
import com.example.esc_50005.UI.Dashboard.main.DeleteCourseItemListener;
import com.example.esc_50005.UI.Login.LoginPresenter;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class ProfessorDashboardFragment extends Fragment implements DashboardContract.View, View.OnClickListener {

    // UI references.
    SharedPreferences sharedPreferences;
    private DashboardContract.Presenter mPresenter;
    private CoursesAdapter mCoursesAdapter;
    private RecyclerView coursesListRecycler;
    private ProfessorDashboardFragment.LayoutManagerType mCurrentLayoutManagerType;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button button;
    private Button deleteCourse;
    private SwipeRefreshLayout swipeLayout;

    public ProfessorDashboardFragment() {
    }

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPresenter = new DashboardPresenter(
                Injection.provideUsersInformationRepository(getActivity().getApplicationContext()),
                Injection.provideCoursesRepository(getActivity().getApplicationContext()),
                this)
        ;
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

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.courses_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                attemptLoadCourses();
            }
        });

        attemptLoadCourses();
        return view;
    }

    public void coursesLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        mCoursesAdapter.notifyDataSetChanged();
    }

    public void attemptLoadCourses()
    {
        mPresenter.loadCoursesFromDatabase(
                sharedPreferences.getString(getString(R.string.user_id),""));
    }


    public void showSuccessfullyLoadedCourses(ArrayList<String> coursesList)
    {
        mCoursesAdapter=new CoursesAdapter(coursesList,mItemListener,this.getActivity().getApplicationContext(),mDeleteCourseListener);
        coursesListRecycler.setAdapter(mCoursesAdapter);
    }

    public void showUnsuccessfullyLoadedCourses()
    {
        //haven't written code that opens the dashboard if login is successful
    }

    public void showAddValidNewCourse()
    {
        attemptLoadCourses();
    }

    public void showLoadedCourses() {

    }

    DeleteCourseItemListener mDeleteCourseListener = new DeleteCourseItemListener() {
        @Override
        public void deleteCourse(String courseId,String courseName) {
            mPresenter.deleteCourse(courseId,courseName);
        }

    };
    CoursesItemListener mItemListener = new CoursesItemListener() {
        @Override
        public void moveToCourseScreen(String clickedCourse) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.course_full_name), clickedCourse);
            editor.commit();
            Intent intent = new Intent(getActivity(), CourseActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View view) {

        switch(sharedPreferences.getString((getString(R.string.user_type)),"")){
            case "professor":
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
                        mPresenter.queryCourseBeforeAdding(
                                sharedPreferences.getString(getString(R.string.user_type),""), courseId.getText().toString(),
                                courseName.getText().toString());
                        dialog.cancel();
                    }
                });
                layout.addView(courseId);
                layout.addView(courseName);
                alertDialog.setView(layout);
                alertDialog.create();
                alertDialog.show();
                break;

            case "student":
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this.getActivity());
                alertDialog2.setTitle("Add New Course");
                LinearLayout layout2 = new LinearLayout(this.getActivity());
                layout2.setOrientation(LinearLayout.VERTICAL);

                final EditText courseId2 = new EditText(getActivity().getApplicationContext());
                courseId2.setHint("Course Id");
                alertDialog2.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Log.i("start query","start query");
                        mPresenter.queryCourseBeforeAdding(
                                sharedPreferences.getString(getString(R.string.user_type),""),
                                courseId2.getText().toString(),"");
                        dialog.cancel();
                    }
                });
                layout2.addView(courseId2);
                alertDialog2.setView(layout2);
                alertDialog2.create();
                alertDialog2.show();
                break;

        }


    }
    public void showAddInvalidCourse()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getActivity());
        alertDialog.setTitle("Course already exists in the database!");
        alertDialog.create();
        alertDialog.show();
    }

    @Override
    public void showEmptyCourses() {

    }

}





