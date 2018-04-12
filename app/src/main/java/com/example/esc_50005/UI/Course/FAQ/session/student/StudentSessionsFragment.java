package com.example.esc_50005.UI.Course.FAQ.session.student;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.esc_50005.Database.sessionsInformation.SessionsInformationDO;
import com.example.esc_50005.Database.sessionsInformation.SessionsInformationDataSource;
import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsAdapter;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsContract;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsPresenter;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class StudentSessionsFragment extends Fragment implements SessionsContract.View, View.OnClickListener {
    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SessionsContract.Presenter mPresenter;
    private RecyclerView sessionsListRecycler;
    private ImageButton button;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout swipeLayout;
    private FloatingActionButton fab;

    private SessionsAdapter mSessionsAdapter;

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    public StudentSessionsFragment() {
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClick();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull SessionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("student yay","student yay");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        View view=inflater.inflate(R.layout.course_session_fragment, container, false);
        sessionsListRecycler=(RecyclerView) view.findViewById(R.id.sessions_recycler);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        sessionsListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.sessions_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                attemptQuerySessions();
            }
        });

        fab= getActivity().findViewById(R.id.course_fab);

        button= view.findViewById(R.id.add_sessions);
        button.setOnClickListener(this);
        attemptQuerySessions();
        return view;
    }

    public void attemptQuerySessions()
    {
        mPresenter.querySessions(
                sharedPreferences.getString(getString(R.string.user_id),""),
                sharedPreferences.getString(getString(R.string.course_full_name),""));
    }


    @Override
    public void showSessions(ArrayList<SessionsInformationDO> sessions) {
        mSessionsAdapter=new SessionsAdapter(sessions,this.getContext());
        sessionsListRecycler.setAdapter(mSessionsAdapter);
    }

    @Override
    public void showEmptySessions() {

    }

    public void showLoadSessionsError()
    {

    }

    @Override
    public void showSuccessfulAddNewSession() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Session added, new session id is "+ sharedPreferences.getString("AddedSessionId",""));
        mSessionsAdapter.notifyDataSetChanged();
        attemptQuerySessions();
        builder.create();
        builder.show();
    }

    @Override
    public void showUnsuccessfulAddNewSession() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Session does not exist!");
        builder.create();
        builder.show();
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Add new session");
        LinearLayout layout = new LinearLayout(this.getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText sessionId = new EditText(getActivity().getApplicationContext());
        sessionId.setHint("Session Id");

        builder.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                mPresenter.queryAddNewSession(sharedPreferences.getString(getString(R.string.user_type),""),sessionId.getText().toString(),"","","");
                dialog.cancel();
            }
        });
        layout.addView(sessionId);
        builder.setView(layout);
        layout.setId(R.id.layout_add_session);
        builder.create();
        builder.show();
    }

    public void sessionsLoaded() {
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }

    public void setFab() {
        fab= getActivity().findViewById(R.id.course_fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClick();
            }
        });
    }

    public void onFabClick() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
        builder.setTitle("Add new session");
        LinearLayout layout = new LinearLayout(this.getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText sessionId = new EditText(getActivity().getApplicationContext());
        sessionId.setHint("Session Id");

        builder.setNegativeButton("Submit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                mPresenter.queryAddNewSession(sharedPreferences.getString(getString(R.string.user_type),""),sessionId.getText().toString(),"","","");
                dialog.cancel();
            }
        });
        layout.addView(sessionId);
        builder.setView(layout);
        layout.setId(R.id.layout_add_session);
        builder.create();
        builder.show();
    }
}
