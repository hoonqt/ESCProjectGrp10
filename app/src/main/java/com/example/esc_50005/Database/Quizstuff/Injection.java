package com.example.esc_50005.Database.Quizstuff;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Otter on 1/4/2018.
 */

public class Injection {

    public static QuizRemoteDataSource provideQuizRepository(@NonNull Context context) {
        checkNotNull(context);
        return QuizRemoteDataSource.getInstance();
    }
}
