package com.example.dev3rema.mvpandroidlang.ui.main;

import com.example.dev3rema.mvpandroidlang.base.AppBasePresenter;
import com.example.dev3rema.mvpandroidlang.base.AppBaseView;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

public interface MainContract {

    interface View extends AppBaseView<Presenter> {
        void setData(List<Lang> langs);
    }

    interface Presenter extends AppBasePresenter {

        void getData();

        void deleteLang(Lang lang);
    }
}
