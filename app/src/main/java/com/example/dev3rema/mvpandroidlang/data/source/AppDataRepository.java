package com.example.dev3rema.mvpandroidlang.data.source;

import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

/**
 * Concrete implementation to load data.
 */
public class AppDataRepository implements AppDataSource {

    private static AppDataRepository INSTANCE = null;

    private final AppDataSource mLocal;

    // Prevent direct instantiation.
    private AppDataRepository(@NonNull AppDataSource appLocalDataSource) {
        mLocal = appLocalDataSource;
    }


    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param appLocalDataSource the device storage data source
     * @return the {@link AppDataRepository} instance
     */
    public static AppDataRepository getInstance(AppDataSource appLocalDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new AppDataRepository(appLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Force destroy instance of the class.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getLang(final GetLangsCallback callback) {

        mLocal.getLang(new GetLangsCallback() {
            @Override
            public void onLoaded(List<Lang> numbers) {
                callback.onLoaded(numbers);
            }
        });
    }

    @Override
    public void saveLang(Lang number, final SavedCallback callback) {
        mLocal.saveLang(number, new SavedCallback() {
            @Override
            public void onResult() {
                callback.onResult();
            }
        });
    }

    @Override
    public void deleteLang(Lang number, final SavedCallback callback) {
        mLocal.deleteLang(number, new SavedCallback() {
            @Override
            public void onResult() {
                callback.onResult();
            }
        });
    }
}