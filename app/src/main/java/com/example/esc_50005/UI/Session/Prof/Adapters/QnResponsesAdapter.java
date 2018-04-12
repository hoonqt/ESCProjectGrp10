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
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;

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
        public TextView nameBox;
        public TextView dateBox;

        public ResponsesViewHolder(View v) {
            super(v);
            answerBox = (TextView) v.findViewById(R.id.answers);
            nameBox = (TextView) v.findViewById(R.id.nameBox);
            dateBox = (TextView) v.findViewById(R.id.dateBox);
        }

        public void bind(int position) {
            answerBox.setText(input.get(position).getAnswer());

            String dateOfResponse = input.get(position).getTime();

            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");

            Date currentDate = new Date();
            Date responseDate = new Date();

            try {
                responseDate = formatter.parse(dateOfResponse);
            }

            catch (ParseException ex) {

            }

            Map<TimeUnit,Long> mappedDiff = computeDiff(responseDate,currentDate);

            if (mappedDiff.get(DAYS) >0) {
                Long days = mappedDiff.get(DAYS);
                String noofdats = Long.toString(days);
                dateBox.setText(noofdats + " days ago");
            }

            else if (mappedDiff.get(HOURS) >0) {
                Long hours = mappedDiff.get(HOURS);
                String noofdats = Long.toString(hours);
                dateBox.setText(noofdats + " hours ago");

            }

            else if (mappedDiff.get(MINUTES) >0) {
                Long minutes = mappedDiff.get(MINUTES);
                String noofdats = Long.toString(minutes);
                dateBox.setText(noofdats + " minutes ago");

            }

            else {
                dateBox.setText("Recently");
            }

            nameBox.setText(input.get(position).getName());
        }

        public Map<TimeUnit,Long> computeDiff(Date date1, Date date2) {
            long diffInMillies = date2.getTime() - date1.getTime();
            List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
            Collections.reverse(units);
            Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
            long milliesRest = diffInMillies;
            for ( TimeUnit unit : units ) {
                long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
                long diffInMilliesForUnit = unit.toMillis(diff);
                milliesRest = milliesRest - diffInMilliesForUnit;
                result.put(unit,diff);
            }
            return result;
        }

    }

}
