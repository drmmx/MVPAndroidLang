package com.example.dev3rema.mvpandroidlang.ui.addlang;

import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.AppDataSource;

import java.util.List;

/**
 * Created by dev3rema
 */
public class AddLangPresenter implements AddLangContract.Presenter {

    private final AppDataSource mRepository;

    private final AddLangContract.View mView;

    public AddLangPresenter(@NonNull AppDataSource repository,
                         @NonNull AddLangContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void addLang(String name, String description) {
        Lang lang1 = new Lang(name, description);
        mRepository.saveLang(lang1, new AppDataSource.SavedCallback() {
            @Override
            public void onResult() {
                // TODO: refresh adapter
            }
        });
    }

    @Override
    public void start() {

    }
}
