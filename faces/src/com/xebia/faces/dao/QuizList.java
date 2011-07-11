package com.xebia.faces.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizList {
    private static final long serialVersionUID = 2640875173418551093L;
    private final List<QuizItem> items;

    public QuizList(List<QuizItem> items) {
        this.items = items;
    }

    public QuizList() {
        this.items = new ArrayList<QuizItem>();
    }

    public QuizList shuffleAndSelect(int number) {
        Collections.shuffle(items);
        return new QuizList(items.subList(0, number));
    }

    public QuizItem get(int i) {
        return items.get(i);
    }

    public void add(QuizItem quizItem) {
        items.add(quizItem);
    }

    public int size() {
        return this.items.size();
    }


}
