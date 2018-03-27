package com.example.cindy.esc_50005.UI.Course.FAQ;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cindy.esc_50005.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */

public class ProgressStudentFragment extends Fragment implements ProgressContract.View {

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    private final String TAG = "ProgressStudentFragment";
    private ProgressContract.Presenter mPresenter = new ProgressPresenter(this);
    private BarChart mChart;
    public ProgressStudentFragment() {
        // Required empty public constructor
    }

    public static ProgressStudentFragment newInstance() {
        return new ProgressStudentFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        Log.i(TAG, "onResume");

    }

    @Override
    public void setPresenter(@NonNull ProgressContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        Log.i(TAG, "setPresenter");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_progress_main, container, false);
        mChart = (BarChart)view.findViewById(R.id.chart1);
//        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.faq_swipe);
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.loadScores();
//            }
//        });
        Log.i(TAG, "onCreateView");


        return view;
    }

        private void setData(ArrayList<Double> scoreList){
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
            Log.i("zain", "scoreList size1 " + scoreList.size());
        for(int i=0; i<scoreList.size(); i++){
            double score = scoreList.get(i);
            Log.i("zain", "score " + score);
            float value = (float) score/5*100;
            yVals.add(new BarEntry(i,value));
        }

        BarDataSet set = new BarDataSet(yVals, "(Quizzes)");
        set.setDrawIcons(false);
        set.setColors(ColorTemplate.LIBERTY_COLORS);
        //Show value on top of each bar
        set.setDrawValues(true);
        set.setValueTextSize(10);
//        set.setBarBorderWidth(1f);

        BarData data = new BarData(set);
//        data.setBarWidth(0.5f);
        data.setValueFormatter(new MyValueFormatter());
        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);
            Log.i(TAG, "setData");

        }



    @Override
    public void showProgress(final ArrayList<Double> scoreList) {

            Log.i(TAG, "hi" + scoreList.size());

        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(40);

        final String[] ds = new String[scoreList.size()];
        Log.i("zain", "scoreList size " + scoreList.size());
        for(int i=0; i<scoreList.size();i++){
            ds[i]= "Quiz " + (i+1);
        }


        XAxis xval = mChart.getXAxis();
        xval.setDrawLabels(false);

//        xval.setValueFormatter(new MyAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                Log.i("zain", "value " + value);
////                if(value>scoreList.size()){
////                    return ds[Math.round(value)-1];
////                }else {
////                    return ds[Math.round(value)];
////                }
//                return ds[1];
//
//
//            }
//
////            @Override
////            public int getDecimalDigits() {
////                return 0;
////            }
//        });

        setData(scoreList);
        mChart.setFitBars(false);

//        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
//        session.addQuestion("What is the difference between Observer and Strategy Design Pattern?","111");

    }

    @Override
    public void showAverage() {

    }

    // TO BE REMOVED (cant remove yet due to QuestionsFragment using it)
    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }
}
//public class ProgressStudentFragment extends Fragment implements ProgressContract.View {
//
//    private SessionQuestionsRemoteDataSource progressRepository= new SessionQuestionsRemoteDataSource();
//    private ProgressContract.Presenter mPresenter = new ProgressPresenter(progressRepository,this);
//    private BarChart mChart;
//
//    public ProgressStudentFragment() {
//        // Required empty public constructor
//    }
//
//    public static ProgressStudentFragment newInstance() {
//        return new ProgressStudentFragment();
//    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();
//    }
//
//    @Override
//    public void setPresenter(@NonNull ProgressContract.Presenter presenter) {
//        mPresenter = checkNotNull(presenter);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.my_progress_main, container, false);
//        mChart = (BarChart)view.findViewById(R.id.chart1);
//
//        return view;
//    }
//
//    private void setData(int count){
//        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
//
//        for(int i=0; i<count; i++){
//            float value = (i * 20 );
//            yVals.add(new BarEntry(i,value));
//        }
//
//        BarDataSet set = new BarDataSet(yVals, "(Quizzes)");
//        set.setDrawIcons(false);
//        set.setColors(ColorTemplate.LIBERTY_COLORS);
//        //Show value on top of each bar
//        set.setDrawValues(true);
//        set.setValueTextSize(15);
//
//        BarData data = new BarData(set);
//        data.setValueFormatter(new MyValueFormatter());
//        mChart.setData(data);
//        mChart.invalidate();
//        mChart.animateY(500);
//    }
//
//
//
//    @Override
//    public <T> void showProgress(T data) {
//
//        mChart.getDescription().setEnabled(false);
//        mChart.setMaxVisibleValueCount(40);
//
//        final String[] ds = new String[5];
//        ds[0]="Quiz 1";
//        ds[1]="Quiz 2";
//        ds[2]="Quiz 3";
//        ds[3]="Quiz 4";
//        ds[4]="Quiz 5";
//
//
//        XAxis xval = mChart.getXAxis();
//        xval.setDrawLabels(true);
//        xval.setValueFormatter(new MyAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                Log.i("zain", "value " + value);
//                return ds[Math.round(value)];
//            }
//
////            @Override
////            public int getDecimalDigits() {
////                return 0;
////            }
//        });
//
//        setData(5);
//        mChart.setFitBars(true);
//
////        SessionQuestionsRemoteDataSource session= new SessionQuestionsRemoteDataSource();
////        session.addQuestion("What is the difference between Observer and Strategy Design Pattern?","111");
//
//    }
//
//    @Override
//    public void showAverage() {
//
//    }
//
//}
