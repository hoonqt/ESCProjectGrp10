package com.example.cindy.esc_50005.UI.ProfSession.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.ProfSession.ProfSessionActivity;
import com.example.cindy.esc_50005.UI.ProfSession.QuizStuff;
import com.example.cindy.esc_50005.UI.ProfSession.SideScreens.ActivityInfo;

import java.util.ArrayList;

/**
 * Created by hoonqt on 27/3/18.
 */

public class QuizEditorAdapter extends RecyclerView.Adapter<QuizEditorAdapter.QuizViewHolder>{
    private static int viewHolderCount = 0;
    private Context context;
    private int noofentries = 1;

    public QuizEditorAdapter() {

    }


    // Create new views (invoked by the layout manager)

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.recycler_quizeditor;
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
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return noofentries;
    }

    public void incEntries() {
        noofentries++;
    }

    class QuizViewHolder extends RecyclerView.ViewHolder {

        public EditText mQuestion;


        public QuizViewHolder(View v) {
            super(v);
            mQuestion = v.findViewById(R.id.question);


        }

        public void bind(int position) {

        }

    }



}

