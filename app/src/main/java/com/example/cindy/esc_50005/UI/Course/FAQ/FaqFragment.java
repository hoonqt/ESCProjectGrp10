package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Session.SessionActivity;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class FaqFragment extends Fragment implements FaqContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SessionQuestionsRemoteDataSource faqRepository= new SessionQuestionsRemoteDataSource();
    private FaqContract.Presenter mPresenter = new FaqPresenter(faqRepository, this);
    private LinearLayout mFaqView;
    private RecyclerView faqListRecycler;

    private FaqAdapter mFaqAdapter;
    Button clickToGoToSessions;

    ArrayList<FaqJsonData> FaqList;

    public FaqFragment() {
        // Required empty public constructor
    }

    public static FaqFragment newInstance() {
        return new FaqFragment();
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
    public void setPresenter(@NonNull FaqContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.faq_main, container, false);
        faqListRecycler=(RecyclerView) view.findViewById(R.id.recyclerViewFaqs);
        mLayoutManager= new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        faqListRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));



        return view;
    }

    @Override
    public <T> void showFaq(T data) {

        mFaqAdapter=new FaqAdapter(getContext(),data);
        faqListRecycler.setAdapter(mFaqAdapter);

//        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
//        session.addQuestion("What is the difference between Observer and Strategy Design Pattern?","111");

    }

    public void showNoFaq()
    {


    }
    public void showLoadFaqError()
    {

    }


    public void AddItemsToRecyclerViewArrayList(){

        FaqList = new ArrayList<>();
    }

    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }

}
