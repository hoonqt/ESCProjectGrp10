package com.example.cindy.esc_50005.UI.Course.FAQ.session.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.R;
import com.example.cindy.esc_50005.UI.Course.FAQ.CourseActivity;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqItemListener;
import com.example.cindy.esc_50005.UI.Session.Main.SessionActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by cindy on 27/11/2017.
 */

public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.SessionsViewHolder> implements View.OnClickListener {

    public static final String TAG = "FaqAdapter";

    private ArrayList<String> mSessionsList;

    private static int viewHolderCount = 0;
    private Context context;
    private SharedPreferences sharedPreferences;

    public SessionsAdapter(ArrayList<String> sessions, Context context){
        this.mSessionsList = sessions;
        this.context=context;
    }


    public void onBindViewHolder(SessionsViewHolder holder, int position) {
        holder.bind(position);
    }

    //indicates how many list objects it has

    public int getItemCount() {
        if (mSessionsList == null) {
            return 0;
        } else {
            return mSessionsList.size();
        }
    }

    public SessionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutIDForListItem = R.layout.course_session_recycler;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIDForListItem,parent,shouldAttachToParentImmediately);
        SessionsViewHolder sessionViewHolder = new SessionsViewHolder(view);
        viewHolderCount++;

        return sessionViewHolder;
    }

    @Override
    public void onClick(View view) {

    }

    class SessionsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView session_card;


        SessionsViewHolder(View v) {

            super(v);
            v.setOnClickListener(this);
            //we need the v object because the view contains the references to the widgets that we need
            session_card = (TextView) v.findViewById(R.id.session_details);

        }

        public void bind(int position) {

            String session = mSessionsList.get(position);
            session_card.setText(session);
        }

        @Override
        public void onClick(View v) {
            Log.i("clicked in pager","clicked in pager");
            int clickedPosition=getAdapterPosition();
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SessionSelected", mSessionsList.get(clickedPosition));
            editor.commit();
            //session should inflate the corresponding session activity
            context.startActivity(new Intent(context, SessionActivity.class));
        }
    }
}