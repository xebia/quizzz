package com.xebia.faces.test;

import junit.framework.TestCase;

import com.xebia.faces.QuizActivity;

public class QuizActivityTest extends TestCase {
    public void testBaseNameWithoutExtension() {
        String bananaFile = QuizActivity.baseNameWithoutExtension("banana.file.extension");
        assertEquals("banana.file", bananaFile);
        assertEquals("banana", QuizActivity.baseNameWithoutExtension("banana.extension"));
        assertEquals("banana", QuizActivity.baseNameWithoutExtension("banana"));
    }

}
