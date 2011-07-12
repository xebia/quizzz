package com.xebia.faces.dao;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.xebia.faces.R;


public class QuizItem {
    private final String pictureAssetName;
    private final String name;

    public QuizItem(String name, String picture) {
        this.name = name;
        this.pictureAssetName = picture;
    }

    @Override
    public boolean equals(Object o) {
        QuizItem otherItem = (QuizItem) o;
        return otherItem.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getPictureAssetsName() {
        return pictureAssetName;
    }

    public String getName() {
        return name;
    }
    private Bitmap getBitmapFromAsset(Context context, String strName) {
        AssetManager assetManager = context.getAssets();
        Bitmap bitmap = null;
        try {
            InputStream istr;
            istr = assetManager.open(strName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.icon)).getBitmap();
        }
        return bitmap;
    }

    public void setImageBitmapOn(Context context, ImageView picture) {
        picture.setImageBitmap(getBitmapFromAsset(context, this.getPictureAssetsName()));
    }
}
