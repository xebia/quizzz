package com.xebia.faces.dao;


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
}
