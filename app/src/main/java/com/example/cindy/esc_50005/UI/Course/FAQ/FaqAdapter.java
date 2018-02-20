package com.example.cindy.esc_50005.UI.Course.FAQ;

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
 * Created by cindy on 27/11/2017.
 */

//TODO 4.1 (in a separate XML File) Design your list item layout

//TODO 4.2 go back to activity_main.xml and put in the recycler view widget

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {

    private FaqFragment.FaqJsonData[] data;
    private static int viewHolderCount = 0;
    Context parentContext;

    //constructor needs the context and the data
    FaqAdapter(Context context, FaqFragment.FaqJsonData[] data){
        this.parentContext = context;
        this.data=data;
    }

    //references are created to the individual widgets by the instantiation
    //joins data to the widgets

    @Override
    public void onBindViewHolder(FaqViewHolder holder, int position) {
        Log.i("position", Integer.toString(position));
        holder.bind(position);
    }

    //indicates how many list objects it has
    @Override
    public int getItemCount() {
        //vary the value by putting 1,2,3
        return data.length;
    }

    //inflates the layout
    //instantiates the view holder object
    @Override
    public FaqAdapter.FaqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        int layoutIDForListItem = R.layout.faq_recycler;
        LayoutInflater inflater = LayoutInflater.from(parentContext);
        boolean shouldAttachToParentImmediately = false;

        //java object of layout
        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
        FaqViewHolder faqViewHolder = new FaqViewHolder(view);
        viewHolderCount++;

        return faqViewHolder;
    }


    class FaqViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView question;
        TextView answer;


        FaqViewHolder(View v){

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            question = (TextView) v.findViewById(R.id.item_question);
            answer = (TextView) v.findViewById(R.id.item_answer);

            v.setOnClickListener(this);

        }

        public void bind(int position ){

            question.setText(data[position].question);
            answer.setText(data[position].answer) ;


        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            AlertDialog.Builder builder=new AlertDialog.Builder(parentContext);

            String question=data[clickedPosition].question;
            builder.setMessage("Question: " + question );

            AlertDialog alertDialog=builder.create();
            //instantiates the object
            alertDialog.show();

        }
    }
}