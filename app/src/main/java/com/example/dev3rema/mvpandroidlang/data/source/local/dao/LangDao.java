package com.example.dev3rema.mvpandroidlang.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

@Dao
public interface LangDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveLang(Lang lang);

    @Delete
    void deleteLang(Lang lang);

    @Query("SELECT * FROM android_lang")
    List<Lang> getLangs();

    @Query("SELECT * FROM android_lang WHERE id = :id")
    Lang getLangById(int id);
}