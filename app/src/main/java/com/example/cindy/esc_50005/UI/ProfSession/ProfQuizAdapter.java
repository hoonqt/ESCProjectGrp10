package com.example.cindy.esc_50005.UI.ProfSession;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ProfQuizAdapter extends RecyclerView.Adapter<ProfQuizAdapter.QuizViewHolder> {

    private ArrayList<QuizStuff> dataset;
    private static int viewHolderCount = 0;
    private Context context;

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

        context = parent.getContext();

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

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTextView;
        public Button lunchPad;

        public QuizViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.quizname);
            lunchPad = (Button)v.findViewById(R.id.moreinfobutton);
            lunchPad.setOnClickListener(this);

        }

        public void bind(int position) {

            QuizStuff question = dataset.get(position);
            mTextView.setText(question.getName());

        }

        @Override
        public void onClick(View v) {

            if (v.getId() == lunchPad.getId()) {

                QuestionAdder adder = new QuestionAdder();
                ProfSessionActivity myActivity = (ProfSessionActivity)context;
                myActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_out_up,R.anim.slide_in_up).replace(R.id.profsessionhere,adder).addToBackStack(null).commit();
                Log.i("Donald","This ain't working");

            }

        }
    }



}
