package com.example.dev3rema.mvpandroidlang.data.source.local;

import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.AppDataSource;
import com.example.dev3rema.mvpandroidlang.data.source.local.dao.LangDao;
import com.example.dev3rema.mvpandroidlang.util.AppExecutors;

import java.util.List;


/**
 * Concrete implementation of a data source as a db.
 */
public class AppLocalDataSource implements AppDataSource {

    private static volatile AppLocalDataSource INSTANCE;

    private LangDao mLangDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation
    private AppLocalDataSource(@NonNull AppExecutors appExecutors,
                               @NonNull LangDao langDao) {
        mAppExecutors = appExecutors;
        mLangDao = langDao;
    }

    /**
     * Singleton pattern
     */
    public static AppLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                 @NonNull LangDao langDao) {
        if (INSTANCE == null) {
            synchronized (AppLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppLocalDataSource(appExecutors,
                            langDao);
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void getAllLangs(final GetAllLangsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Lang> langs = mLangDao.getLangs();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onLoaded(langs);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveLang(final Lang lang) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mLangDao.saveLang(lang);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteLang(final Lang number, final SavedCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mLangDao.deleteLang(number);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResult();
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getLangById(final int id, final GetLangByIdCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Lang lang = mLangDao.getLangById(id);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onLoaded(lang);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }


}