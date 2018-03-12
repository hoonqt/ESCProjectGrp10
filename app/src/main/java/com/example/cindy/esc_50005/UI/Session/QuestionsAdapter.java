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
    private QuestionsPresenter.QuestionsJsonData[] dataInClass;


    <T>  QuestionsAdapter(Context context, T data){
        this.parentContext = context;
        final Class<QuestionsPresenter.QuestionsJsonData[]> questionsJsonDataType = QuestionsPresenter.QuestionsJsonData[].class;
        final QuestionsPresenter.QuestionsJsonData[] instance = convertInstanceOfObject(data, questionsJsonDataType);
        dataInClass=instance;
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(com.example.cindy.esc_50005.UI.Session.QuestionsAdapter.QuestionsViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return dataInClass.length;
    }

    @Override
    public QuestionsAdapter.QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.sessionquestions_postquestions_recycler;
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

            super(v);

            question = (TextView) v.findViewById(R.id.item_post_question);

            v.setOnClickListener(this);

        }

        public void bind(int position ){
            Log.i("data position",Integer.toString(dataInClass.length));
            Log.i("question to be posted",dataInClass.toString());
            question.setText(dataInClass[position]._question);

        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            AlertDialog.Builder builder=new AlertDialog.Builder(parentContext);

            String question=dataInClass[clickedPosition]._question;
            Log.i("question clicked","question clicked");
            builder.setMessage("Question: " + question);

            AlertDialog alertDialog=builder.create();
            //instantiates the object
            alertDialog.show();

        }



    }
}
