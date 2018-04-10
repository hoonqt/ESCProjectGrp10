package com.example.esc_50005.UI.Session.Prof.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esc_50005.Database.QuizAnswers.QuizAnswersDO;
import com.example.esc_50005.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class QnResponsesAdapter extends RecyclerView.Adapter<QnResponsesAdapter.ResponsesViewHolder> {

    private ArrayList<QuizAnswersDO> input;
    private static int viewHolderCount = 0;
    private Context context;

    public QnResponsesAdapter(ArrayList<QuizAnswersDO> input) {
        this.input = input;

        Collections.sort(input, new Comparator<QuizAnswersDO>() {
            @Override
            public int compare(QuizAnswersDO quizAnswersDO, QuizAnswersDO t1) {

                SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

                String date1str = quizAnswersDO.getTime();
                String date2str = t1.getTime();

                Date date1 = new Date();
                Date date2 = new Date();

                try {
                    date1 = formatter.parse(date1str);
                    date2 = formatter.parse(date2str);
                }

                catch (ParseException ex) {

                }



                return date1.compareTo(date2);
            }
        });

    }

    @NonNull
    @Override
    public ResponsesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.responses_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        viewHolderCount++;

        return new ResponsesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponsesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (input == null) return 0;

        else return input.size();
    }

    class ResponsesViewHolder extends RecyclerView.ViewHolder {

        public TextView answerBox;

        public ResponsesViewHolder(View v) {
            super(v);
            answerBox = (TextView) v.findViewById(R.id.answers);
        }

        public void bind(int position) {
            answerBox.setText(input.get(position).getAnswer());
        }

    }

}
