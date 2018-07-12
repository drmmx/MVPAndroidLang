package com.example.dev3rema.mvpandroidlang.ui.main;

import android.support.annotation.NonNull;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.data.source.AppDataSource;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final AppDataSource mRepository;

    private final MainContract.View mView;

    public MainPresenter(@NonNull AppDataSource repository,
                         @NonNull MainContract.View view) {
        mRepository = repository;
        mView = view;
    }

    @Override
    public void start() {
        // entry function
        getData();
    }

    @Override
    public void getData() {
        mRepository.getAllLangs(new AppDataSource.GetAllLangsCallback() {
            @Override
            public void onLoaded(List<Lang> langs) {
                mView.setData(langs);
            }
        });
    }

    @Override
    public void deleteLang(Lang number) {
        mRepository.deleteLang(number, new AppDataSource.SavedCallback() {
            @Override
            public void onResult() {
                // TODO
                getData();
            }
        });
    }
}
