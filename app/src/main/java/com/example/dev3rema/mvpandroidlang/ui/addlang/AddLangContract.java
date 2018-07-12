package com.example.dev3rema.mvpandroidlang.ui.addlang;

import com.example.dev3rema.mvpandroidlang.base.AppBasePresenter;
import com.example.dev3rema.mvpandroidlang.base.AppBaseView;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

/**
 * Created by dev3rema
 */
public interface AddLangContract {

    interface View extends AppBaseView<Presenter> {

        void enterValues(Lang lang);

        void setData(List<Lang> langs);
    }

    interface Presenter extends AppBasePresenter {

        void addLang(String name, String description);

    }

}
