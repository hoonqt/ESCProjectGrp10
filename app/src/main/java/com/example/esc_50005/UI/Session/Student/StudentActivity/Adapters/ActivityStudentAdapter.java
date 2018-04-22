package com.example.esc_50005.UI.Session.Student.StudentActivity.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.esc_50005.Database.QuizAnswers.QuizAnswersDO;
import com.example.esc_50005.Database.QuizAnswers.QuizAnswersRemoteDataSource;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Log;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Student.StudentActivity.SideScreens.AnsweringZoneFrag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ActivityStudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<QuizQuestions2DO> dataset;
    private ArrayList<String> names;
    private ArrayList<QuizQuestions2DO> smallerdataset;
    private static int viewHolderCount = 0;
    private Context context;
    SharedPreferences sharedPreferences;

    public ActivityStudentAdapter(ArrayList<QuizQuestions2DO> input) {

        names = new ArrayList<>();
        dataset = input;

        smallerdataset = new ArrayList<>();

        for (int i = 0;i<dataset.size();i++) {

            if (dataset.get(i).getIsItQn()) {
                names.add(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]);
                smallerdataset.add(dataset.get(i));
            }

            else if (!(names.contains(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]))) {
                names.add(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]);
                smallerdataset.add(dataset.get(i));
            }
        }

    }

    public void setData(ArrayList<QuizQuestions2DO> input) {
        this.dataset.clear();
        dataset.addAll(input);
        names.clear();
        smallerdataset.clear();

        for (int i = 0;i<dataset.size();i++) {

            if (dataset.get(i).getIsItQn()) {
                names.add(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]);
                smallerdataset.add(dataset.get(i));
            }

            else if (!(names.contains(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]))) {
                names.add(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]);
                smallerdataset.add(dataset.get(i));
            }
        }

    }

    @Override
    public int getItemViewType(int position) {

        if (smallerdataset.get(position).getIsItQn()) {
            return 0;
        }

        else return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.studquiz_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        viewHolderCount++;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());

        switch (viewType) {
            case 0: return new QuestionViewHolder(view);
            case 1: return new QuizViewHolder(view);
            default: return null;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == 0) {
            QuestionViewHolder viewholder = (QuestionViewHolder)holder;
            viewholder.bind(position);
        }
        else {
            QuizViewHolder viewholder1 = (QuizViewHolder)holder;
            viewholder1.bind(position);
        }
    }

    @Override
    public int getItemCount() {
        if (names == null) {
            return 0;
        }

        else return names.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView quizName;
        private ImageView goBox;

        public QuestionViewHolder(View v) {
            super(v);
            quizName = v.findViewById(R.id.quizname);
            goBox = v.findViewById(R.id.nxtpgarrow);

            quizName.setOnClickListener(this);
            goBox.setOnClickListener(this);

        }

        public void bind(int position) {
            quizName.setText(names.get(position));
        }

        @Override
        public void onClick(View view) {

            AlertDialog.Builder popupBuilder = new AlertDialog.Builder(context);
            popupBuilder.setTitle("Enter answer");
            popupBuilder.setMessage(smallerdataset.get(getAdapterPosition()).getQuestion());

            final EditText input = new EditText(context);
            input.setHint("Answer");
            popupBuilder.setView(input);

            popupBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
                    Date date = new Date();
                    String formatted = formatter.format(date);

                    QuizAnswersDO answers = new QuizAnswersDO();
                    String name = sharedPreferences.getString(context.getString(R.string.full_name),null);
                    String studentID = sharedPreferences.getString(context.getString(R.string.user_id),null);

                    answers.setName(name);
                    answers.setAnswer(input.getText().toString());
                    answers.setQuizNameStudentID(names.get(getAdapterPosition())+studentID);
                    answers.setTime(formatted);
                    answers.setSubjectCodeSessionCode(smallerdataset.get(getAdapterPosition()).getSubjectCodeSessionCode());

                    QuizAnswersRemoteDataSource uploader = new QuizAnswersRemoteDataSource();
                    uploader.addQuestion(answers);

                    dialogInterface.dismiss();
                }
            });

            popupBuilder.show();

        }
    }

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView quizName;
        private ImageView goBox;

        public QuizViewHolder(View v) {
            super(v);
            quizName = v.findViewById(R.id.quizname);
            goBox = v.findViewById(R.id.nxtpgarrow);

            quizName.setOnClickListener(this);
            goBox.setOnClickListener(this);

        }

        public void bind(int position) {
            quizName.setText(names.get(position));

        }

        @Override
        public void onClick(View view) {

            AnsweringZoneFrag editor = new AnsweringZoneFrag();
            Bundle bundler = new Bundle();

            SharedPreferences.Editor editshared = sharedPreferences.edit();
            editshared.putString(context.getString(R.string.quiz_name),names.get(getAdapterPosition()));
            editshared.commit();

            ArrayList<QuizQuestions2DO> tobetransferred = new ArrayList<>(dataset);

            bundler.putSerializable("allthequestions",tobetransferred);
            editor.setArguments(bundler);

            SessionActivity myActivity = (SessionActivity) context;
            myActivity.getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.profsessionhere,editor).commit();

        }
    }
}
