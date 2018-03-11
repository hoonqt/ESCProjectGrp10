package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.cindy.esc_50005.Database.questionMethods;
import com.example.cindy.esc_50005.R;

/**
 * Created by cindy on 27/11/2017.
 */

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {
    private FaqPresenter.FaqJsonData[] dataInClass;

    private static int viewHolderCount = 0;
    Context parentContext;

    <T> FaqAdapter(Context context, T data){
        this.parentContext = context;
        final Class<FaqPresenter.FaqJsonData[]> faqJsonDataType = FaqPresenter.FaqJsonData[].class;
        final FaqPresenter.FaqJsonData[] instance = convertInstanceOfObject(data, faqJsonDataType);
        dataInClass=instance;
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }


    public void onBindViewHolder(FaqViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has

    public int getItemCount() {
        return dataInClass.length;
    }


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


        FaqViewHolder(View v) {

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            question = (TextView) v.findViewById(R.id.item_question);
            answer = (TextView) v.findViewById(R.id.item_answer);

            v.setOnClickListener(this);

        }

        public void bind(int position) {

            question.setText(dataInClass[position]._question);
//            answer.setText(dataInClass[position]._answers);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);

            String question = dataInClass[clickedPosition]._question;
            builder.setMessage("Question: " + question);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}