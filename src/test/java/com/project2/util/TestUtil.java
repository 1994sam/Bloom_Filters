package com.project2.util;

import com.project2.elements.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Helper for tester classes.
 */
public class TestUtil {

    /**
     * Generates elements with random destination address.
     *
     * @param number the number of elements to be generated.
     * @return the generated elements.
     */
    public static List<Element> getElements(int number) {
        Random r = new Random();
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(new Element(r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256)));
        }
        return list;
    }
}
