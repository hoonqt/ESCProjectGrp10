package com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by hoonqt on 7/4/18.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.AnsViewHolder> {

    private ArrayList<QuizQuestions2DO> dataset;
    private ArrayList<Integer> userOptions;
    private static int viewHolderCount = 0;
    private Context context;

    public ResultsAdapter(ArrayList<QuizQuestions2DO> input,ArrayList<Integer> useroptions) {
        dataset = new ArrayList<>(input);
        userOptions = new ArrayList<>(useroptions);
    }

    @NonNull
    @Override
    public AnsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.studentans_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
        AnsViewHolder ansViewHolder = new AnsViewHolder(view);
        viewHolderCount++;

        return ansViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AnsViewHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        if (dataset ==  null) {
            return 0;
        }

        else return dataset.size();
    }

    class AnsViewHolder extends RecyclerView.ViewHolder {

        TextView questionBox;

        TextView option1ans;
        TextView option2ans;
        TextView option3ans;
        TextView option4ans;

        ImageView option1box;
        ImageView option2box;
        ImageView option3box;
        ImageView option4box;




        public AnsViewHolder(View v) {
            super(v);

            questionBox = v.findViewById(R.id.questionBox);

            option1ans = v.findViewById(R.id.option1ans);
            option2ans = v.findViewById(R.id.option2ans);
            option3ans = v.findViewById(R.id.option3ans);
            option4ans = v.findViewById(R.id.option4ans);

            option1box = v.findViewById(R.id.option1pic);
            option2box = v.findViewById(R.id.option2pic);
            option3box = v.findViewById(R.id.option3pic);
            option4box = v.findViewById(R.id.option4pic);


        }

        public void bind(int position) {

            QuizQuestions2DO question = dataset.get(position);


            questionBox.setText(question.getQuestion());

            option1ans.setText(question.getOptions().get(0));
            option2ans.setText(question.getOptions().get(1));
            option3ans.setText(question.getOptions().get(2));
            option4ans.setText(question.getOptions().get(3));

            Integer selected = userOptions.get(position);

            if (selected == 0) {
                option1box.setImageResource(R.drawable.ic_close_black_24dp);
            }

            else if (selected == 1) {
                option2box.setImageResource(R.drawable.ic_close_black_24dp);
            }

            else if (selected == 2) {
                option3box.setImageResource(R.drawable.ic_close_black_24dp);
            }

            else if (selected == 3) {
                option4box.setImageResource(R.drawable.ic_close_black_24dp);
            }

            double option = question.getCorrectans();

            if (option == 0) {
                option1box.setImageResource(R.drawable.ic_check_box_black_24dp);
            }

            else if (option == 1) {
                option2box.setImageResource(R.drawable.ic_check_box_black_24dp);
            }

            else if (option == 2) {
                option3box.setImageResource(R.drawable.ic_check_box_black_24dp);
            }

            else if (option == 3) {
                option4box.setImageResource(R.drawable.ic_check_box_black_24dp);
            }


        }
    }
}
