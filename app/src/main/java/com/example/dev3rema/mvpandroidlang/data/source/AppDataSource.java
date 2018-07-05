package com.example.dev3rema.mvpandroidlang.data.source;


import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

public interface AppDataSource {

    void getLang(GetLangsCallback callback);

    void saveLang(Lang lang, SavedCallback callback);

    void deleteLang(Lang lang, SavedCallback callback);

    interface SavedCallback {
        void onResult();
    }

    interface GetLangsCallback {
        void onLoaded(List<Lang> langs);
    }
}
