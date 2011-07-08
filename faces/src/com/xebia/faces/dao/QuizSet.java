package com.xebia.faces.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizSet extends ArrayList<QuizItem> {
    private static final long serialVersionUID = 2640875173418551093L;

    public List<QuizItem> randomlySelectSomeItems() {
        Collections.shuffle(this);
        return Arrays.asList(
            this.get(0),
            this.get(1),
            this.get(2)
            );
    }
}
