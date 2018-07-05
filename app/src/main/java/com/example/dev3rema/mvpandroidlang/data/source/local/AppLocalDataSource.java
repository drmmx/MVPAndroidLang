package com.example.dev3rema.mvpandroidlang.data.source.local;

import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.AppDataSource;
import com.example.dev3rema.mvpandroidlang.data.source.local.dao.PhoneNumberDao;
import com.example.dev3rema.mvpandroidlang.util.AppExecutors;

import java.util.List;


/**
 * Concrete implementation of a data source as a db.
 */
public class AppLocalDataSource implements AppDataSource {

    private static volatile AppLocalDataSource INSTANCE;

    private PhoneNumberDao mPhone;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation
    private AppLocalDataSource(@NonNull AppExecutors appExecutors,
                               @NonNull PhoneNumberDao phoneNumberDao) {
        mAppExecutors = appExecutors;
        mPhone = phoneNumberDao;
    }

    /**
     * Singleton pattern
     */
    public static AppLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                 @NonNull PhoneNumberDao phoneNumberDao) {
        if (INSTANCE == null) {
            synchronized (AppLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppLocalDataSource(appExecutors,
                            phoneNumberDao);
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void getLang(final GetLangsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Lang> numbers = mPhone.getNumbers();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onLoaded(numbers);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveLang(final Lang number, final SavedCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPhone.saveLang(number);
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
    public void deleteLang(final Lang number, final SavedCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPhone.deleteLang(number);
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
}