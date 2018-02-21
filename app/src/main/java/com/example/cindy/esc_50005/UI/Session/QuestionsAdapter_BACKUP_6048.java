package com.example.cindy.esc_50005.UI.Session;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
=======
import android.widget.EditText;
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae
import android.widget.TextView;

import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqFragment;

import org.w3c.dom.Text;

/**
 * Created by 1002215 on 20/2/18.
 */

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private QuestionsFragment.QuestionsJsonData[] data;
    private static int viewHolderCount = 0;
    Context parentContext;
    private QuestionsFragment.QuestionsJsonData[] data;

    //TODO 4.4 - Constructor
    //constructor needs the context and the data
<<<<<<< HEAD
    QuestionsAdapter(Context context,QuestionsFragment.QuestionsJsonData[]  data){
=======
    QuestionsAdapter(Context context, QuestionsFragment.QuestionsJsonData[] data){
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae
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
<<<<<<< HEAD
       TextView question;
=======
        TextView question;
        TextView answer;
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae

        QuestionsViewHolder(View v){

            //TODO 4.3 Invoke the superclass constructor
            // and get references to the various widgets in the List Item Layout
            super(v);
<<<<<<< HEAD
            question = (TextView) v.findViewById(R.id.item_post_question);
=======
            //we need the v object because the view contains the references to the widgets that we need
            question = (TextView) v.findViewById(R.id.item_question);
            answer = (TextView) v.findViewById(R.id.item_answer);
>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae
            v.setOnClickListener(this);

        }

        //TODO 4.6 - write a bind method to attach content
        //            to the respective widgets
        public void bind(int position ){
<<<<<<< HEAD
            Log.i("data",data[position].question);
            question.setText(data[position].question);
=======

            question.setText(data[position].question);
            answer.setText(data[position].answer);

>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae
        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            AlertDialog.Builder builder=new AlertDialog.Builder(parentContext);

<<<<<<< HEAD
=======
            String question=data[clickedPosition].question;
            builder.setMessage("Question: " + question);

>>>>>>> d9821ecc2426fdd040fb296cdd30d2c9c1c65aae
            AlertDialog alertDialog=builder.create();
            //instantiates the object
            alertDialog.show();

        }



    }
}
