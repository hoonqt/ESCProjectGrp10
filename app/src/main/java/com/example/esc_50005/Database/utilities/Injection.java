package com.example.esc_50005.Database.utilities;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.Progress.ProgressRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.Database.sessionsInformation.SessionsInformationRemoteDataSource;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Otter on 1/4/2018.
 */

public class Injection {
//    public static FaqRepository provideFaqRepository(@NonNull Context context) {
//        checkNotNull(context);
//        ToDoDatabase database = ToDoDatabase.getInstance(context);
//        return FaqRepository.getInstance(FakeFaqRemoteDataSource.getInstance(),
//                FaqLocalDataSource.getInstance(new AppExecutors(),
//                        database.taskDao()));
//    }

    public static FaqRemoteDataSource provideFaqRepository(@NonNull Context context) {
        checkNotNull(context);
//        FaqRoomDatabase database = FaqRoomDatabase.getInstance(context);
        return FaqRemoteDataSource.getInstance();
    }

    public static SessionsInformationRemoteDataSource provideSessionsRepository(@NonNull Context context) {
        checkNotNull(context);
        return SessionsInformationRemoteDataSource.getInstance();
    }

    public static UsersInformationRemoteDataSource provideUsersInformationRepository(@NonNull Context context) {
        checkNotNull(context);
        return UsersInformationRemoteDataSource.getInstance();
    }
    public static CoursesInformationRemoteDataSource provideCoursesRepository(@NonNull Context context) {
        checkNotNull(context);
        return CoursesInformationRemoteDataSource.getInstance();
    }
    public static ProgressRemoteDataSource provideProgressRepository(@NonNull Context context) {
        checkNotNull(context);
        return ProgressRemoteDataSource.getInstance();
    }

}
