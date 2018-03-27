package com.example.cindy.esc_50005.UI.Course.FAQ.session.professor;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SessionsFragment extends Fragment implements SessionsContract.View {


    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SessionsContract.Presenter mPresenter = new SessionsPresenter(this);
    private LinearLayout mSessionsView;
    private RecyclerView sessionsListRecycler;
    private SwipeRefreshLayout swipeLayout;

    private SessionsAdapter mSessionsAdapter;

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
        attemptQuerySessions();
        return view;
    }

    public void attemptQuerySessions()
    {
        mPresenter.querySessions(getActivity().getApplicationContext());
    }

    public void showSessions(ArrayList<String> sessions) {
        Log.i("showing","showing");
        Log.i("sessions",Integer.toString(sessions.size()));
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


}
