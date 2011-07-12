package com.xebia.faces.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.xebia.faces.R;


public class AssetQuizItem implements QuizItem {
    private final String pictureAssetName;
    private final String name;

    public AssetQuizItem(String picture) {
        this.name = AssetQuizItem.baseNameWithoutExtension(picture);
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

    @Override
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

    @Override
    public void setImageBitmapOn(Context context, ImageView picture) {
        picture.setImageBitmap(getBitmapFromAsset(context, this.getPictureAssetsName()));
    }

    public static String baseNameWithoutExtension(String pictureAssetName) {
        Uri uri = Uri.parse(pictureAssetName);
        List<String> segments = uri.getPathSegments();
        String basename = segments.get(segments.size() -1);
        int lastDot = basename.lastIndexOf('.');
        if (lastDot < 0) {
            return pictureAssetName;
        }
        String name = basename.substring(0, lastDot);
        return name;
    }
}
