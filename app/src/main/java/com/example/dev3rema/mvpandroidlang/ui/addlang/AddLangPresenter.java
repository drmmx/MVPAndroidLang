package com.example.dev3rema.mvpandroidlang.ui.addlang;

import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.AppDataSource;

/**
 * Created by dev3rema
 */
public class AddLangPresenter implements AddLangContract.Presenter {

    private final AppDataSource mRepository;

    private final AddLangContract.View mView;

    private String mLangId;

    public AddLangPresenter(String langId, @NonNull AppDataSource repository,
                         @NonNull AddLangContract.View view) {
        mLangId = langId;
        mRepository = repository;
        mView = view;
    }

    @Override
    public void saveLang(Lang lang) {
        mRepository.saveLang(lang);
    }

    @Override
    public void start() {
        if (mLangId != null) {
            mRepository.getLangById(
                    Integer.valueOf(mLangId), new AppDataSource.GetLangByIdCallback() {
                        @Override
                        public void onLoaded(Lang lang) {
                            mView.enterValues(lang);
                        }
                    });
        } else {
            Lang lang = new Lang("", "");
            mView.enterValues(lang);
        }
    }
}
