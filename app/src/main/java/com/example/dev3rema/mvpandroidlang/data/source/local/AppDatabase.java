package com.example.dev3rema.mvpandroidlang.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.local.dao.PhoneNumberDao;


/**
 * The Room Database.
 */
@Database(entities = {
        Lang.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;

    /**
     * Singleton pattern
     */
    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        "app.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract PhoneNumberDao phoneNumberDao();
}