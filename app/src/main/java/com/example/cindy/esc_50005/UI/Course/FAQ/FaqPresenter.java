//package com.example.cindy.esc_50005.UI.Course.FAQ;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.example.cindy.esc_50005.R;
//import com.google.gson.Gson;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
///**
// * Created by cindy on 19/2/2018.
// */
//
//public class FaqPresenter implements FaqContract.FaqContractPresenter {
//
//    private final FaqContract.FaqContractView mFaqView;
//    private final FaqRepository mFaqRepository;
//    FaqFragment.FaqJsonData[] faqJsonData;
//
//    public FaqPresenter(@NonNull FaqRepository faqRepository, @NonNull TasksContract.View faqView) {
//        mFaqRepository = checkNotNull(faqRepository, "tasksRepository cannot be null");
//        mFaqView = checkNotNull(faqView, "faqView cannot be null!");
//
//        mFaqView.setPresenter(this);
//    }
//    @Override
//    public void start()
//    {
//
//    }
//
//    public class FaqJsonData {
//
//        String question;
//        String answer;
//        String upvotes;
//
//    }
//
//    private String readTxt(int resource) {
//
//        InputStream inputStream = getResources().openRawResource(resource);
//        //TODO 3.2 Complete readTxt to take in a resource ID of a file,
//        //          read it and return it as a single string
//        // Reads an InputStream and converts it to a String.
//
//        String line;
//        String output="";
//
//        try{
//            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
//            while( (line = reader.readLine()) != null){
//                output = output + line;
//            }
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return output;
//
//    }
//
//
//    public void loadFaq(){
//        Gson gson = new Gson();
//            //Gson is a Java library that can be used to convert Java Objects into their JSON representation.
//            // It can also be used to convert a JSON string to an equivalent Java object.
//            //TODO 3.3 Invoke readTxt
//        String myJsonData=readTxt(R.raw.faq);
//        Log.i("myJsonData",myJsonData);
//
//            //TODO 3.4 parse the JSON file
//        faqJsonData=gson.fromJson(myJsonData, FaqJsonData[].class);
//        Log.i("type",faqJsonData.getClass().getName());
//
//    }
//    public void processEmptyFaq()
//    {
//
//    }
//    public void upvoteFaq()
//    {
//
//    }
//
//}
