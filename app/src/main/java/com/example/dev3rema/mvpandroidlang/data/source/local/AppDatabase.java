package com.example.dev3rema.mvpandroidlang.data.source.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.local.dao.LangDao;

import java.util.concurrent.Executors;


/**
 * The Room Database.
 */
@Database(entities = {Lang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LangDao langDao();

    private static final Object sLock = new Object();
    private static AppDatabase INSTANCE;

    /**
     * Singleton pattern
     */
    public static AppDatabase getInstance(final Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "app.db")
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        getInstance(context).langDao().insertAll(Lang.populateData());
                                    }
                                });
                            }
                        })
                        .build();
            }
            return INSTANCE;
        }
    }
}