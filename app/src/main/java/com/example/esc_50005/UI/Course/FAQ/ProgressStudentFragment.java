package com.example.esc_50005.UI.Course.FAQ;


import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esc_50005.Database.utilities.Injection;
import com.example.esc_50005.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
    private String courseId;
//    private ProgressContract.Presenter mPresenter = new ProgressPresenter(this);
    private BarChart mChart;
    private SharedPreferences userInformation;
    private ProgressContract.Presenter mPresenter;
    private FloatingActionButton fab;
    public ProgressStudentFragment() {
        // Required empty public constructor
    }

    public static ProgressStudentFragment newInstance() {
        return new ProgressStudentFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProgressPresenter(
                Injection.provideProgressRepository(getActivity().getApplicationContext()), this);

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
        String studentId;
        userInformation = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        if(userInformation.getString(getString(R.string.user_type),"").equals("professor")) {
            if (savedInstanceState == null) {
                Bundle extras = getActivity().getIntent().getExtras();
                if(extras == null) {
                    studentId= null;
                } else {
                    studentId= extras.getString("STUDENT_ID");
                }
            } else {
                studentId= (String) savedInstanceState.getSerializable("STUDENT_ID");
            }
        } else{
            studentId = userInformation.getString(getString(R.string.user_id), "");
        }
        courseId = userInformation.getString(getString(R.string.course_full_name),"");
        Log.i(TAG, "STUDENT_ID: " + studentId);
        Log.i(TAG, "Course ID: " + courseId);

        mChart = (BarChart)view.findViewById(R.id.chart1);
//        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.faq_swipe);
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.loadScores();
//            }
//        });
        Log.i(TAG, "onCreateView");
        mPresenter.setStudentId(studentId.substring(0,7));
        mPresenter.setCourseId(courseId.substring(0,6));

        fab = (FloatingActionButton) getActivity().findViewById(R.id.course_fab);


        return view;
    }

        private void setData(ArrayList<Double> scoreList){
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
            Log.i("setData", "scoreList size1 " + scoreList.size());
        for(int i=0; i<scoreList.size(); i++){
            double score = scoreList.get(i);
            Log.i("setData", "score " + score);
            float value = (float) score;
            yVals.add(new BarEntry(i,value));
        }

        BarDataSet set = new BarDataSet(yVals, "(Quizzes)");
        set.setDrawIcons(false);
//        set.setColors(getResources().getColor(R.color.colorPrimary));
        set.setColors(ColorTemplate.JOYFUL_COLORS);
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

        if(scoreList.size()!=0){
            mChart.getDescription().setEnabled(false);
            mChart.setMaxVisibleValueCount(40);

            final String[] ds = new String[scoreList.size()];
            Log.i("ShowProgress", "scoreList size " + scoreList.size());
            for(int i=0; i<scoreList.size();i++){
                ds[i]= "Quiz " + (i+1);
            }

            XAxis xval = mChart.getXAxis();
            xval.setDrawLabels(false);
            mChart.getAxisLeft().setAxisMaximum(100);
            mChart.getAxisLeft().setAxisMinimum(0);
            mChart.getAxisRight().setAxisMaximum(100);
            mChart.getAxisRight().setAxisMinimum(0);

            setData(scoreList);
            mChart.setFitBars(false);
        }
//        mChart.setVisibleYRangeMaximum(100, YAxis.AxisDependency.LEFT);
        mChart.setNoDataText("There are no results currently");
        Paint p = mChart.getPaint(Chart.PAINT_INFO);
        p.setTextSize(50);
        p.setColor(getResources().getColor(R.color.colorPrimary));
//        p.setTypeface(...);

//        QuestionRemoteDataSource session= new QuestionRemoteDataSource();
//        session.addQuestion("What is the difference between Observer and Strategy Design Pattern?","111");

    }

    @Override
    public void showAverage(double avg) {

    }

    @Override
    public void showNames(ArrayList<String> nameList, ArrayList<String> studentIds, ArrayList<Double> avg) {

    }

    @Override
    public void showNoName() {

    }

    // TO BE REMOVED (cant remove yet due to QuestionsFragment using it)
    public class FaqJsonData {

        String question;
        String answer;
        String upvotes;

    }

    public void setFab() {
        fab.setVisibility(View.GONE);
    }
}
