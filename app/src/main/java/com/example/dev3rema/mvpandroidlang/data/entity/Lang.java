package com.example.dev3rema.mvpandroidlang.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "android_lang")
public class Lang {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String description;

    /**
     * Constructor for new lang
     */
    @Ignore
    public Lang(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor for update name
     */
    public Lang(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
