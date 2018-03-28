package com.example.cindy.esc_50005.UI.Course.FAQ.session.professor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqAdapter;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqFragment;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqItemListener;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.example.cindy.esc_50005.UI.Course.FAQ.addEditFaq.AddEditFaqActivity;
import com.example.cindy.esc_50005.UI.Course.FAQ.session.main.SessionsAdapter;
import com.example.cindy.esc_50005.UI.Course.FAQ.session.main.SessionsContract;
import com.example.cindy.esc_50005.UI.Course.FAQ.session.main.SessionsItemListener;
import com.example.cindy.esc_50005.UI.Course.FAQ.session.main.SessionsPresenter;
import com.example.cindy.esc_50005.UI.Session.Main.SessionActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SessionsFragment extends Fragment implements SessionsContract.View, View.OnClickListener {
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SessionsContract.Presenter mPresenter = new SessionsPresenter(this);
    private LinearLayout mSessionsView;
    private RecyclerView sessionsListRecycler;
    private SwipeRefreshLayout swipeLayout;
    private ImageButton button;

    private SessionsAdapter mSessionsAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public SessionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View view=inflater.inflate(R.layout.course_session_fragment, container, false);
        sessionsListRecycler=(RecyclerView) view.findViewById(R.id.sessions_recycler);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        sessionsListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        button= view.findViewById(R.id.add_button);
        button.setOnClickListener(this);
        attemptQuerySessions();
        return view;
    }

    public void attemptQuerySessions()
    {
        mPresenter.querySessions(getActivity().getApplicationContext());
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
    public void showSuccessfulAddNewSession() {
        attemptQuerySessions();
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
        final EditText sessionId = new EditText(getActivity().getApplicationContext());
        sessionId.setHint("Session Id");
        final EditText sessionName = new EditText(getActivity().getApplicationContext());
        sessionName.setHint("Session Name");

        builder.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {

                String sessionIdToAdd=sessionId.getText().toString();
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
                mPresenter.addNewSession(sessionIdToAdd,sessionNameToAdd,timeOfCreation.toString(),"50.005");
                dialog.cancel();
            }
        });
        layout.addView(sessionId);
        layout.addView(sessionName);
        builder.setView(layout);
        builder.create();
        builder.show();
    }



}
