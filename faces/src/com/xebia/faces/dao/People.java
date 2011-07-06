package com.xebia.faces.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class People extends ArrayList<Person> {
    private static final long serialVersionUID = 2640875173418551093L;

    public List<Person> randomlySelectSomePeople() {
        Collections.shuffle(this);
        return Arrays.asList(
            this.get(0),
            this.get(1),
            this.get(2)
            );
    }
}
