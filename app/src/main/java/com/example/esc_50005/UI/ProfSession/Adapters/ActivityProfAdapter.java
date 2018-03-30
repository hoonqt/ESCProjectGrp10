package com.example.esc_50005.UI.ProfSession.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.ProfSession.SideScreens.ActivityInfo;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.WebSocket.WebSocket;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ActivityProfAdapter extends RecyclerView.Adapter<ActivityProfAdapter.QuizViewHolder> {

    private ArrayList<QuizQuestions1DO> dataset;
    private ArrayList<String> names;
    private static int viewHolderCount = 0;
    private Context context;
    SharedPreferences sharedPreferences;

    public ActivityProfAdapter(ArrayList<QuizQuestions1DO> input) {

        names = new ArrayList<>();
        dataset = input;

        for (int i = 0;i<dataset.size();i++) {
            if (!names.contains(dataset.get(i).getQuizName())) {
                names.add(dataset.get(i).getQuizName());
            }
        }


    }


    // Create new views (invoked by the layout manager)
    @Override
    public ActivityProfAdapter.QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.profquiz_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QuizViewHolder quizViewHolder = new QuizViewHolder(view);
        viewHolderCount++;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());



        return quizViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ActivityProfAdapter.QuizViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (dataset == null) {
            return 0;
        }
        else return dataset.size();
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public TextView lunchPad;
        public Switch switchUp;

        public QuizViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.quizname);
            lunchPad = (TextView) v.findViewById(R.id.moreinfobutton);
            switchUp = (Switch) v.findViewById(R.id.profquiztoggle) ;
            lunchPad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("currentquiz",mTextView.toString());
                    editor.putString("currentSession",dataset.get(0).getSubjectCodeSessionCode());
                    editor.commit();

                    ActivityInfo adder = new ActivityInfo();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("allthequestions",dataset);
                    adder.setArguments(bundle);
                    SessionActivity myActivity = (SessionActivity) context;
                    myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.slide_out_up,R.anim.slide_in_up).replace(R.id.profsessionhere,adder).addToBackStack(null).commit();
                    Log.i("Donald","This ain't working");

                }
            });

            switchUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    WebSocket websock = new WebSocket();
                    if (isChecked) {
                        websock.start();
                    }

                    else {
                        websock.end();
                    }
                }
            });

        }

        public void bind(int position) {

            QuizQuestions1DO question = dataset.get(position);
            mTextView.setText(question.getQuizName());

        }


    }

}
