package com.example.cindy.esc_50005.UI.Session;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;

/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private QuestionsFragment.QuestionsJsonData[] data;
    private static int viewHolderCount = 0;
    Context parentContext;

    //TODO 4.4 - Constructor
    //constructor needs the context and the data
    QuestionsAdapter(Context context, QuestionsFragment.QuestionsJsonData[] data){
        this.parentContext = context;
        this.data=data;
    }

    //@Override
    //public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //    return null;
    //}

    //TODO 4.7 - onBindViewHolder
    //references are created to the individual widgets by the instantiation
    //joins data to the widgets

    @Override
    public void onBindViewHolder(com.example.cindy.esc_50005.UI.Session.QuestionsAdapter.QuestionsViewHolder holder, int position) {
        //TODO invoke bind method in inner class
        Log.i("position", Integer.toString(position));
        holder.bind(position);
//        Log.i("checkIfEmpty",);
    }

    //TODO 4.8 - getItemCount
    //indicates how many list objects it has
    @Override
    public int getItemCount() {
        //vary the value by putting 1,2,3
        return data.length;
    }

    //TODO 4.5 - onCreateViewHolder
    //inflates the layout
    //instantiates the view holder object
    @Override
    public QuestionsAdapter.QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.ques_recycler;
        LayoutInflater inflater = LayoutInflater.from(parentContext);
        boolean shouldAttachToParentImmediately = false;

        //java object of layout
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);

        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(view);
        viewHolderCount++;
        Log.i("Cindy","OnCreateViewHolder"+viewHolderCount);

        return questionsViewHolder;
    }


    class QuestionsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView question;
        TextView answer;

        QuestionsViewHolder(View v){

            //TODO 4.3 Invoke the superclass constructor
            // and get references to the various widgets in the List Item Layout
            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            question = (TextView) v.findViewById(R.id.item_question);
            answer = (TextView) v.findViewById(R.id.item_answer);
            v.setOnClickListener(this);
            //wants object of onclick interface

            //since not possible to have it in the xml to specify the button
            //implement interface annoynomously
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            //now not neccessary since we make our class implement it now no longer annoynymously

        }

        //TODO 4.6 - write a bind method to attach content
        //            to the respective widgets
        public void bind(int position ){

            question.setText(data[position].question);
            answer.setText(data[position].answer);

        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            AlertDialog.Builder builder=new AlertDialog.Builder(parentContext);

            String question=data[clickedPosition].question;
            builder.setMessage("Question: " + question);

            AlertDialog alertDialog=builder.create();
            //instantiates the object
            alertDialog.show();

        }



    }
}
