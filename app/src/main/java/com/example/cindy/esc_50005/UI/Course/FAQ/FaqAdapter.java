package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cindy.esc_50005.R;

/**
 * Created by cindy on 27/11/2017.
 */

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {
//    DynamoDBMapper dynamoDBMapper;
    private FaqFragment.FaqJsonData[] data;

    private static int viewHolderCount = 0;
    Context parentContext;

    FaqAdapter(Context context, FaqFragment.FaqJsonData[] data){
        this.parentContext = context;
        this.data=data;
    }

    @Override
    public void onBindViewHolder(FaqViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has
    @Override
    public int getItemCount() {
        return data.length;
    }

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


        FaqViewHolder(View v) {

            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            question = (TextView) v.findViewById(R.id.item_question);
            answer = (TextView) v.findViewById(R.id.item_answer);

            v.setOnClickListener(this);

        }

        public void bind(int position) {

            question.setText(data[position].question);
            answer.setText(data[position].answer);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);

            String question = data[clickedPosition].question;
            builder.setMessage("Question: " + question);

            AlertDialog alertDialog = builder.create();
            //instantiates the object
            alertDialog.show();

//            questionMethods questions=new questionMethods();
//            questions.postQuestion("What is the difference between decorator and strategy pattern?", "1111");
//
//            DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
//                    new ProfileCredentialsProvider()));
//            Table table = dynamoDB.getTable("ProductList");
//            escproject-mobilehub-27166461-newfaq


        }
    }
}