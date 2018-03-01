package com.example.cindy.esc_50005.UI.Session;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.cindy.esc_50005.R;


/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private static int viewHolderCount = 0;
    Context parentContext;
    private QuestionsFragment.QuestionsJsonData[] data;

    //TODO 4.4 - Constructor
    //constructor needs the context and the data

    QuestionsAdapter(Context context, QuestionsFragment.QuestionsJsonData[] data){
        this.parentContext = context;
        this.data=data;
    }


    //TODO 4.7 - onBindViewHolder
    //references are created to the individual widgets by the instantiation
    //joins data to the widgets

    @Override
    public void onBindViewHolder(com.example.cindy.esc_50005.UI.Session.QuestionsAdapter.QuestionsViewHolder holder, int position) {
        //TODO invoke bind method in inner class
        holder.bind(position);
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

        return questionsViewHolder;
    }


    class QuestionsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

       TextView question;

        QuestionsViewHolder(View v){

            //TODO 4.3 Invoke the superclass constructor
            // and get references to the various widgets in the List Item Layout
            super(v);

            question = (TextView) v.findViewById(R.id.item_post_question);

            v.setOnClickListener(this);

        }

        //TODO 4.6 - write a bind method to attach content
        //            to the respective widgets
        public void bind(int position ){
            Log.i("data",data[position].question);
            question.setText(data[position].question);

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
