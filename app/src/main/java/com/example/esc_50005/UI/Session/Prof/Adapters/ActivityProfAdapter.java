package com.example.esc_50005.UI.Session.Prof.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.Log;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Prof.SideScreens.EditQnListFrag;
import com.example.esc_50005.UI.Session.Prof.SideScreens.ProfResponsesFrag;
import com.example.esc_50005.WebSocket.ProfWebSocket;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ActivityProfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<QuizQuestions2DO> dataset;
    private ArrayList<String> names;
    private ArrayList<QuizQuestions2DO> smallerdataset;
    private static int viewHolderCount = 0;
    private Context context;
    SharedPreferences sharedPreferences;

    public ActivityProfAdapter(ArrayList<QuizQuestions2DO> input, Context context) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

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

    @Override
    public int getItemViewType(int position) {

        if (smallerdataset.get(position).getIsItQn()) {
            return 0;
        }

        else return 1;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.profquiz_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        viewHolderCount++;

        switch (viewType) {
            case 0: return new QuestionViewHolder(view);
            case 1: return new QuizViewHolder(view);

            default: return null;

        }


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

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

                    PopupMenu popup = new PopupMenu(context,lunchPad);
                    popup.inflate(R.menu.quizcreate_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.commit();

                            switch (menuItem.getItemId()) {
                                case (R.id.editbutton):
                                    EditQnListFrag qnListFrag = new EditQnListFrag();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("allthequestions",dataset);
                                    qnListFrag.setArguments(bundle);
                                    SessionActivity myActivity = (SessionActivity) context;
                                    myActivity.getSupportFragmentManager().beginTransaction().replace(R.id.profsessionhere,qnListFrag).addToBackStack(null).commit();
                                    return true;

                                case (R.id.gradesbutton):
                                    return false;
                                    //Insert logic for what not

                            }
                            return false;

                        }
                    });

                    popup.show();
                }
            });


            switchUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });

        }

        public void bind(int position) {

            mTextView.setText(names.get(position));

        }

    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public TextView lunchPad;
        public Switch switchUp;

        public QuestionViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.quizname);
            lunchPad = (TextView) v.findViewById(R.id.moreinfobutton);
            switchUp = (Switch) v.findViewById(R.id.profquiztoggle) ;
            lunchPad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popup = new PopupMenu(context,lunchPad);
                    popup.inflate(R.menu.qnoptions_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {

                            switch (menuItem.getItemId()) {
                                case (R.id.editbutton):
                                    AlertDialog.Builder qnBuilder = new AlertDialog.Builder(context);
                                    qnBuilder.setTitle("Edit question");
                                    qnBuilder.setMessage("Enter question");
                                    final EditText input = new EditText(context);

                                    input.setText(smallerdataset.get(getAdapterPosition()).getQuestion());
                                    qnBuilder.setView(input);

                                    qnBuilder.setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            QuizQuestions2DO tobeadded = new QuizQuestions2DO();
                                            tobeadded.setQuizNameQnID(smallerdataset.get(getAdapterPosition()).getQuizNameQnID());
                                            tobeadded.setSubjectCodeSessionCode(sharedPreferences.getString("Current Course Id", null)+sharedPreferences.getString("Current Session Id",""));
                                            tobeadded.setIsItQn(true);
                                            tobeadded.setQuestion(input.getText().toString());

                                            QuizRemoteDataSource adder = new QuizRemoteDataSource();
                                            adder.putQuestion(tobeadded);

                                            dialogInterface.dismiss();
                                        }
                                    });

                                    qnBuilder.show();
                                    return true;

                                case (R.id.responsesbutton):

                                    ProfResponsesFrag responsesFrag = new ProfResponsesFrag();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("quizName",mTextView.getText().toString());
                                    bundle.putString("questionName",smallerdataset.get(getAdapterPosition()).getQuestion());
                                    responsesFrag.setArguments(bundle);
                                    SessionActivity myActivity = (SessionActivity) context;
                                    myActivity.getSupportFragmentManager().beginTransaction().replace(R.id.profsessionhere,responsesFrag).addToBackStack(null).commit();
                                    return true;

                            }
                            return false;

                        }
                    });

                    popup.show();
                }
            });

        }

        public void bind(int position) {

            mTextView.setText(names.get(position));

        }
    }

}
