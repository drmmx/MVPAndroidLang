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

    public static Lang[] populateData() {
        return new Lang[] {
                new Lang("Java", "Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible."),
                new Lang("Kotlin", "Statically typed programming language for modern multiplatform applications. 100% interoperable with Java™ and Android™"),
                new Lang("C++", "C++ is a high-level programming language developed by Bjarne Stroustrup at Bell Labs"),
                new Lang("JavaScript", "Javascript is a dynamic computer programming language. It is lightweight and most commonly used as a part of web pages, whose implementations allow client-side script to interact with the user and make dynamic pages."),
                new Lang("Dart", "Dart is a general-purpose programming language originally developed by Google and later approved as a standard by Ecma. Dart is an object-oriented, class defined, single inheritance language using a C-style syntax that transcompiles optionally into JavaScript.")
        };
    }
}
