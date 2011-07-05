package com.xebia.faces.dao;

import android.graphics.drawable.Drawable;

public class Person {
    private final Drawable picture;
    private final String name;

    public Person(String name, Drawable picture) {
        this.name = name;
        this.picture = picture;
    }

    public Drawable getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }
}
