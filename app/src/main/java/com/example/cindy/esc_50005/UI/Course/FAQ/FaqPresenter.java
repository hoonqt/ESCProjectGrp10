package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.support.annotation.NonNull;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cindy on 19/2/2018.
 */

public class FaqPresenter implements FaqContract.Presenter {

    private final FaqContract.View mFaqView;
    private SessionQuestionsRemoteDataSource mFaqRepository= new SessionQuestionsRemoteDataSource();
    FaqPresenter.FaqJsonData[] faqJsonData;

    public FaqPresenter(@NonNull SessionQuestionsRemoteDataSource faqRepository, @NonNull FaqContract.View faqView) {
        mFaqRepository = checkNotNull(faqRepository, "faqRepository cannot be null");
        mFaqView = checkNotNull(faqView, "faqView cannot be null!");
        mFaqView.setPresenter(this);
    }
    @Override
    public void start() {
        loadFaq();
    }

    public class FaqJsonData {


        List<String> _answers;
        String _question;
        String upvotes;

    }

    public void loadFaq(){

        ArrayList<JSONObject> answers = mFaqRepository.getDataInJson("111");
        Gson gson = new Gson();
        faqJsonData=gson.fromJson(answers.toString(), FaqJsonData[].class);
        processFaq(faqJsonData);
    }
    public void processEmptyFaq()
    {

    }
    public void upvoteFaq()
    {

    }

    public void processFaq(FaqPresenter.FaqJsonData[] faqJsonData)
    {
        if(faqJsonData.length!=0)
        {
            mFaqView.showFaq(faqJsonData);
        }
    }

}
