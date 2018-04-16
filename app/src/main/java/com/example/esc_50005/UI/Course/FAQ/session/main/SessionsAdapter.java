package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.esc_50005.Database.sessionsInformation.SessionsInformationDO;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Dashboard.main.DeleteCourseItemListener;
import com.example.esc_50005.UI.Session.Main.SessionActivity;

import java.util.ArrayList;

/**
 * Created by cindy on 27/11/2017.
 */

public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.SessionsViewHolder> implements View.OnClickListener {

    public static final String TAG = "FaqAdapter";

    private ArrayList<SessionsInformationDO> mSessionsList;
    private boolean clicked=false;

    private static int viewHolderCount = 0;
    private Context context;
    private SharedPreferences sharedPreferences;
    private ImageButton button;
    private BottomSheetBehavior mBottomSheetBehavior;
    private DeleteSessionItemListener deleteSessionItemListener;

    public SessionsAdapter(ArrayList<SessionsInformationDO> sessions, Context context, DeleteSessionItemListener DeleteItemListener){
        this.mSessionsList = sessions;
        this.context=context;
        this.deleteSessionItemListener=DeleteItemListener;
        Log.i("size",Integer.toString(sessions.size()));
//        for(String session: sessions)
//        {
//            Log.i("the name", session);
//        }
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
        ImageButton button;
        TextView session_date;


        SessionsViewHolder(View v) {

            super(v);
            v.setOnClickListener(this);

            session_card = (TextView) v.findViewById(R.id.session_details);
            session_date= (TextView) v.findViewById(R.id.session_date);

            button=v.findViewById(R.id.click_to_get_options);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button start;
                    Button end;
                    Button delete;
                    view = ((FragmentActivity)context).getLayoutInflater().inflate(R.layout.bottom_sheet, null);
                    start=view.findViewById(R.id.start_session);
                    end=view.findViewById(R.id.end_session);
                    delete=view.findViewById(R.id.delete_session);

                    start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("start","start");

                        }
                    });

                    end.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("end","end");
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteSessionItemListener.deleteSession(mSessionsList.get(getAdapterPosition()).getSessionId(),
                                    mSessionsList.get(getAdapterPosition()).getSessionName(),
                                    mSessionsList.get(getAdapterPosition()).getSessionDate(),
                                    mSessionsList.get(getAdapterPosition()).getSessionStudentList());
                            Log.i("delete","delete");
                        }
                    });

                    BottomSheetDialog dialog = new BottomSheetDialog(context);
                    dialog.setContentView(view);
                    dialog.show();

                    switch( view.getId() ) {
                        case R.id.click_to_get_options: {
                            if(clicked==false)
                            {
                                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                clicked=true;
                                break;
                            }
                            else if(clicked==true)
                            {
                                mBottomSheetBehavior.setPeekHeight(0);
                                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                clicked=false;
                                break;
                            }
                        }

                    }

                }
            });

        }

        public void bind(int position) {
            StringBuilder session=new StringBuilder();
            session.append(mSessionsList.get(position).getSessionDate());
            session.append(" ");
            session.append(mSessionsList.get(position).getSessionName());
            session_date.setText(mSessionsList.get(position).getSessionDate());

            session_card.setText(mSessionsList.get(position).getSessionName());
        }

        @Override
        public void onClick(View v) {

            int clickedPosition=getAdapterPosition();
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(
                    context.getResources().getString(R.string.session_id),
                    mSessionsList.get(clickedPosition).getSessionId());
            editor.putString(
                    context.getResources().getString(R.string.session_name),
                    mSessionsList.get(clickedPosition).getSessionName());
            editor.commit();
            Log.i("this is session name",sharedPreferences.getString(context.getResources().getString(R.string.session_name),""));
            context.startActivity(new Intent(context, SessionActivity.class));
        }
    }
}