package com.example.esc_50005.UI.StudentActivity.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.R;

import java.util.ArrayList;


/**
 * Created by hoonqt on 30/3/18.
 */

public class ActivityStudentAdapter extends RecyclerView.Adapter<ActivityStudentAdapter.QuizViewHolder> {

    private ArrayList<QuizQuestions1DO> dataset;
    private ArrayList<String> names;
    private static int viewHolderCount = 0;
    private Context context;
    SharedPreferences sharedPreferences;

    public ActivityStudentAdapter(ArrayList<QuizQuestions1DO> input) {

        names = new ArrayList<>();
        dataset = input;

        for (int i = 0;i<dataset.size();i++) {
            if (!names.contains(dataset.get(i).getQuizName())) {
                names.add(dataset.get(i).getQuizName());
            }
        }

    }

    @NonNull
    @Override
    public ActivityStudentAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.studquiz_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QuizViewHolder quizViewHolder = new QuizViewHolder(view);
        viewHolderCount++;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());


        return quizViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityStudentAdapter.QuizViewHolder holder, int position) {
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

        private TextView quizName;
        private ImageView goBox;

        public QuizViewHolder(View v) {
            super(v);
            quizName = v.findViewById(R.id.quizname);
            goBox = v.findViewById(R.id.nxtpgarrow);

        }

        public void bind(int position) {
            quizName.setText(names.get(position));

        }


    }
}
