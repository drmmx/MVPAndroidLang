package com.example.dev3rema.mvpandroidlang.data.source;


import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

public interface AppDataSource {

    void getAllLangs(GetAllLangsCallback callback);

    void saveLang(Lang lang);

    void deleteLang(Lang lang, SavedCallback callback);

    void getLangById(int id, GetLangByIdCallback callback);

    interface SavedCallback {
        void onResult();
    }

    interface GetLangByIdCallback {
        void onLoaded(Lang lang);
    }

    interface GetAllLangsCallback {
        void onLoaded(List<Lang> langs);
    }
}
