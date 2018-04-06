package com.example.esc_50005.UI.Session.Prof.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Main.SessionActivity;
import com.example.esc_50005.UI.Session.Prof.SideScreens.EditQnListFrag;
import com.example.esc_50005.WebSocket.ProfWebSocket;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ActivityProfAdapter extends RecyclerView.Adapter<ActivityProfAdapter.QuizViewHolder> {

    private ArrayList<QuizQuestions2DO> dataset;
    private ArrayList<String> names;
    private static int viewHolderCount = 0;
    private Context context;
    SharedPreferences sharedPreferences;

    public ActivityProfAdapter(ArrayList<QuizQuestions2DO> input) {

        names = new ArrayList<>();
        dataset = input;

        for (int i = 0;i<dataset.size();i++) {
            if (!(names.contains(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]))) {
                names.add(dataset.get(i).getQuizNameQnID().split(" ")[0]+ " " +dataset.get(i).getQuizNameQnID().split(" ")[1]);
            }
        }


    }


    // Create new views (invoked by the layout manager)
    @Override
    public ActivityProfAdapter.QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.profquiz_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        context = parent.getContext();

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QuizViewHolder quizViewHolder = new QuizViewHolder(view);
        viewHolderCount++;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());



        return quizViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ActivityProfAdapter.QuizViewHolder holder, int position) {
        holder.bind(position);
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
                            editor.putString("currentquiz",mTextView.toString());
                            editor.putString("currentSession",dataset.get(0).getSubjectCodeSessionCode());
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


                }
            });


            switchUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ProfWebSocket websock = ProfWebSocket.getInstance();
                    if (isChecked) {
                        websock.sendMsg("psenda113");
                    }

                    else {
                        websock.end();
                    }
                }
            });


        }

        public void bind(int position) {

            mTextView.setText(names.get(position));

        }


    }

}