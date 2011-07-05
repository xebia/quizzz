package com.xebia.faces.dao;


public class Person {
    private final String pictureAssetName;
    private final String name;

    public Person(String name, String picture) {
        this.name = name;
        this.pictureAssetName = picture;
    }


    @Override
    public boolean equals(Object o) {
        Person otherPerson = (Person) o;
        return otherPerson.getName().equals(name);
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
