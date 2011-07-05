package com.xebia.faces.dao;

import java.util.Arrays;
import java.util.List;

public class People {
    List<Person> randomlySelectSomePeople(int count) {
        return Arrays.asList(
            new Person("Frederik", null),
            new Person("Willem", null),
            new Person("Henk", null)
            );
    }
}
