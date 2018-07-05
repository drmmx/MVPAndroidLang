package com.example.dev3rema.mvpandroidlang.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.source.AppDataRepository;
import com.example.dev3rema.mvpandroidlang.data.source.local.AppDatabase;
import com.example.dev3rema.mvpandroidlang.data.source.local.AppLocalDataSource;
import com.example.dev3rema.mvpandroidlang.util.AppExecutors;

public class AppDataInjector {

    public static AppDataRepository provideDataRepository(@NonNull Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return AppDataRepository.getInstance(
                AppLocalDataSource.getInstance(
                        new AppExecutors(),
                        database.phoneNumberDao()
                )
        );
    }
}
