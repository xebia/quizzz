package com.xebia.faces.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;


public class QuizList {
    private static final long serialVersionUID = 2640875173418551093L;
    private final List<AssetQuizItem> items;

    public QuizList(List<AssetQuizItem> items) {
        this.items = items;
    }

    public QuizList() {
        this.items = new ArrayList<AssetQuizItem>();
    }

    public QuizList shuffleAndSelect(int number) {
        Collections.shuffle(items);
        return new QuizList(items.subList(0, number));
    }

    public AssetQuizItem get(int i) {
        return items.get(i);
    }

    public void add(AssetQuizItem quizItem) {
        items.add(quizItem);
    }

    public int size() {
        return this.items.size();
    }

    public static QuizList fromAssetsDirectory(Context context, String directory) {
        QuizList quizList = new QuizList();
        try {
            String[] list = context.getResources().getAssets().list(directory);
            for (String pictureAssetName : list) {
                quizList.add(new AssetQuizItem(directory + "/" + pictureAssetName));
            }
        } catch (IOException e) {
            //Empty quizset
        }
        return quizList;
    }


}
