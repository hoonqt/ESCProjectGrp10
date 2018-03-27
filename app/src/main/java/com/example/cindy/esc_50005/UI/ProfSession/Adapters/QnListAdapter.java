package com.example.cindy.esc_50005.UI.ProfSession.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cindy.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

/**
 * Created by hoonqt on 27/3/18.
 */

public class QnListAdapter extends RecyclerView.Adapter<QnListAdapter.QnViewHolder>{

    private static int viewHolderCount = 0;
    private Context context;
    private ArrayList<String> questions;
    SharedPreferences sharedPreferences;

    public QnListAdapter(ArrayList<QuizQuestions1DO> input) {

        questions = new ArrayList<>();
        for (int i = 0;i<input.size();i++) {
            questions.add(input.get(i).getQuestion());
            Log.i("Question",input.get(i).getQuestion());
        }


    }

    @Override
    public QnListAdapter.QnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIDForListItem = R.layout.quizlist_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QnListAdapter.QnViewHolder quizViewHolder = new QnViewHolder(view);
        viewHolderCount++;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());


        return quizViewHolder;
    }

    @Override
    public void onBindViewHolder(QnListAdapter.QnViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (questions == null) {
            return 0;
        }

        else return questions.size();
    }

    class QnViewHolder extends RecyclerView.ViewHolder {

        public TextView QuestionHere;
        public TextView arrowHead;


        public QnViewHolder(View v) {
            super(v);
            QuestionHere = (TextView)v.findViewById(R.id.questionwho);

        }

        public void bind(int position) {

            QuestionHere.setText(questions.get(position));


        }

    }
}
