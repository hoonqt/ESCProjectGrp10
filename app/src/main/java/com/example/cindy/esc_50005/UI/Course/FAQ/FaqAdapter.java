package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cindy.esc_50005.MainActivity;
import com.example.cindy.esc_50005.R;

/**
 * Created by cindy on 27/11/2017.
 */

//TODO 4.1 (in a separate XML File) Design your list item layout

//TODO 4.2 go back to activity_main.xml and put in the recycler view widget

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {

    private static int viewHolderCount = 0;
    Context parentContext;

    //TODO 4.4 - Constructor
    //constructor needs the context and the data
    FaqAdapter(Context context){
        this.parentContext = context;
    }

    //@Override
    //public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //    return null;
    //}

    //TODO 4.7 - onBindViewHolder
    //references are created to the individual widgets by the instantiation
    //joins data to the widgets

    @Override
    public void onBindViewHolder(FaqViewHolder holder, int position) {
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
        return 0;
    }

    //TODO 4.5 - onCreateViewHolder
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
        Log.i("Cindy","OnCreateViewHolder"+viewHolderCount);

        return faqViewHolder;
    }


    class FaqViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        CardView characterName1;
        CardView itemNumber1;
        CardView characterName2;
        CardView itemNumber2;

        FaqViewHolder(View v){

            //TODO 4.3 Invoke the superclass constructor
            // and get references to the various widgets in the List Item Layout
            super(v);
            //we need the v object because the view contains the references to the widgets that we need
            characterName1 = (CardView)itemView.findViewById(R.id.item_text1);
            itemNumber1 = (CardView)itemView.findViewById(R.id.item_count1);
            characterName2 = (CardView)itemView.findViewById(R.id.item_text2);
            itemNumber2 = (CardView)itemView.findViewById(R.id.item_count2);
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


            //TODO 4.6A - get the filename of the image
//            String filename = data[position].file;

            String packageName = parentContext.getPackageName();
            String typeOfResource = "drawable";

            //resId is id of the resource
//            Log.i("fileName",filename);
//            Log.i("packageName",filename);
//            Log.i("typeOfResource",packageName);
//            int resID = parentContext.getResources().getIdentifier(filename, typeOfResource, packageName);
            //TODO 4.6 B pass the resource ID to the image widget
//            picture.setImageResource(resID);

            //TODO 4.6 C pass the character name to the characterName widget
//            characterName.setText(data[position].name);   ;

            //TODO 4.6 D display the position number
//            itemNumber.setText(String.valueOf(position)) ;

        }

        @Override
        public void onClick(View v)
        {
            int clickedPosition=getAdapterPosition();
            AlertDialog.Builder builder=new AlertDialog.Builder(parentContext);

//            String animeName=data[clickedPosition].anime;
//            builder.setMessage("Anime: " + animeName);

            AlertDialog alertDialog=builder.create();
            //instantiates the object
            alertDialog.show();

        }

    }
}