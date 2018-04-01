package com.example.cindy.esc_50005.Database.FAQ;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by Otter on 28/3/2018.
 */

@Database(entities = {Faq.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class FaqRoomDatabase extends RoomDatabase {
    private static FaqRoomDatabase INSTANCE;

    public abstract FaqDao faqDao();

    public static FaqRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FaqRoomDatabase.class, "Faq.db")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
