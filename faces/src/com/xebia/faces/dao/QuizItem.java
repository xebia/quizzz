package com.xebia.faces.dao;

import android.content.Context;
import android.widget.ImageView;


public interface QuizItem {

    public abstract boolean equals(Object o);

    public abstract String getName();

    public abstract void setImageBitmapOn(Context context, ImageView picture);

}
