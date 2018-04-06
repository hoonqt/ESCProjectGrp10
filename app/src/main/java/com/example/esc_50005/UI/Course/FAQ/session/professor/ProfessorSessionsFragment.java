package com.example.esc_50005.UI.Course.FAQ.session.professor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsAdapter;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsContract;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProfessorSessionsFragment extends Fragment implements SessionsContract.View, View.OnClickListener {
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SessionsContract.Presenter mPresenter;
    private LinearLayout mSessionsView;
    private RecyclerView sessionsListRecycler;
    private SwipeRefreshLayout swipeLayout;
    private ImageButton button;
    private SharedPreferences sharedPreferences;
    private BottomSheetBehavior mBottomSheetBehavior;

    private SessionsAdapter mSessionsAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public ProfessorSessionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SessionsPresenter(
                Injection.provideSessionsRepository(getActivity().getApplicationContext()),
                Injection.provideCoursesRepository(getActivity().getApplicationContext()),
                Injection.provideUsersInformationRepository(getActivity().getApplicationContext()),
                this);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull SessionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("professor yay","professor yay");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        View view=inflater.inflate(R.layout.course_session_fragment, container, false);
        sessionsListRecycler=(RecyclerView) view.findViewById(R.id.sessions_recycler);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        sessionsListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        button= view.findViewById(R.id.questions_btn_add);
        button.setOnClickListener(this);
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.sessions_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                attemptQuerySessions();
            }
        });

        attemptQuerySessions();
        return view;
    }


    public void attemptQuerySessions()
    {
        mPresenter.querySessions(
                sharedPreferences.getString(getString(R.string.user_id),""),
                sharedPreferences.getString(getString(R.string.current_course_activity),""));
    }

    public void showSessions(ArrayList<String> sessions) {
        mSessionsAdapter=new SessionsAdapter(sessions,this.getContext());
        sessionsListRecycler.setAdapter(mSessionsAdapter);
    }

    public void showNoSessions()
    {


    }

    @Override
    public void showEmptySessions() {

    }

    public void showLoadSessionsError()
    {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(sharedPreferences.getString(getString(R.string.user_type),"").equals("professor"))
        {
            int id = item.getItemId();

            if(id == R.id.end_session){
                Log.i("end","end");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.end_session), "True");
                editor.commit();
                return true;
            }
            else if(id == R.id.start_session){
                Log.i("end","end");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.delete_session), "True");
                editor.commit();
                return true;
            }
            else if(id == R.id.get_session_id){

                return true;
            }
            else if(id == R.id.delete_session){

                return true;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopup(View v) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Session added, new session id is "+ sharedPreferences.getString(getString(R.string.session_id),""));

        builder.create();
        builder.show();



    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        if(sharedPreferences.getString(getString(R.string.user_type),"").equals("professor"))
//        {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.menu_main, menu);
//            return true;
//        }
//        return false;
//    }

    @Override
    public void showSuccessfulAddNewSession() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Session added, new session id is "+ sharedPreferences.getString(getString(R.string.session_id),""));
        attemptQuerySessions();
        builder.create();
        builder.show();
    }

    @Override
    public void showUnsuccessfulAddNewSession() {

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Add new session");
        LinearLayout layout = new LinearLayout(this.getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText sessionName = new EditText(getActivity().getApplicationContext());
        sessionName.setHint("Session Name");

        builder.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

                Random randomGenerator=new Random();
                int sessionId=100+ randomGenerator.nextInt(100);
                String sessionNameToAdd=sessionName.getText().toString();
                String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                        "Oct", "Nov", "Dec"};
                GregorianCalendar gcalendar = new GregorianCalendar();
                String month=months[gcalendar.get(Calendar.MONTH)];
                int day=gcalendar.get(Calendar.DAY_OF_MONTH);
                StringBuilder timeOfCreation=new StringBuilder();
                timeOfCreation.append(Integer.toString(day));
                timeOfCreation.append(" ");
                timeOfCreation.append(month);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("AddedSessionId",  Integer.toString(sessionId));
                editor.commit();
                String currentCourse=sharedPreferences.getString("CurrentCourseActivity","");
                String[] retrieveCourseId = currentCourse.split("\\s+");
                String courseId=retrieveCourseId[0];
                mPresenter.queryAddNewSession(sharedPreferences.getString("UserType",""),Integer.toString(sessionId),sessionNameToAdd,timeOfCreation.toString(),courseId);
                dialog.cancel();
            }
        });
        layout.addView(sessionName);
        builder.setView(layout);
        builder.create();
        builder.show();
    }

    public void sessionsLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }


}
