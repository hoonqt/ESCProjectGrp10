package com.example.cindy.esc_50005.UI.ProfSession;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ProfQuizAdapter extends RecyclerView.Adapter<ProfQuizAdapter.QuizViewHolder> {

    private ArrayList<QuizStuff> dataset;
    private static int viewHolderCount = 0;

    public ProfQuizAdapter() {

        dataset = new ArrayList<>();
        dataset.add(new QuizStuff("Quiz 1","Who voted leave?"));
        dataset.add(new QuizStuff("Quiz 2","Who voted remain?"));

    }


    // Create new views (invoked by the layout manager)
    @Override
    public ProfQuizAdapter.QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.profquiz_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QuizViewHolder quizViewHolder = new QuizViewHolder(view);
        viewHolderCount++;


        return quizViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProfQuizAdapter.QuizViewHolder holder, int position) {
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

        public QuizViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.quizname);
        }

        public void bind(int position) {

            QuizStuff question = dataset.get(position);
            mTextView.setText(question.getName());

        }

    }



}
