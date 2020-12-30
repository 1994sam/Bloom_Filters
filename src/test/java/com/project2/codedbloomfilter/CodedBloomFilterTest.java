package com.project2.codedbloomfilter;

import com.project2.elements.Element;
import com.project2.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs demo on coded bloom filter.
 */
class CodedBloomFilterTest {

    private CodedBloomFilter<Element> filter;
    private List<List<Element>> elements;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        filter = new CodedBloomFilter<>(7, 7, 30000);
        elements = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            elements.add(TestUtil.getElements(1000));
        }
    }

    @Test
    void Demo() {
        int numberOfSets = 7;
        int numberOfBloomFilters = (int) (Math.log(numberOfSets + 1) / Math.log(2));
        for (int i = 0; i < numberOfSets; i++) {
            String code = toBinary(i + 1, numberOfBloomFilters);
            elements.get(i).forEach(element -> filter.put(element, code));
        }

        long total = 0;
        List<Long> counts = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String code = toBinary(i + 1, numberOfBloomFilters);
            long count = elements.get(i).stream().filter(element -> filter.lookUp(element).equals(code)).count();
            total += count;
            counts.add(count);
        }

        //System.out.println(counts);
        System.out.println("Number of elements found: " + total);
    }

    @Test
    void put() throws NoSuchAlgorithmException {
        filter = new CodedBloomFilter<>(4, 2, 20);
        List<Element> elements = TestUtil.getElements(3);
        for (int i = 0; i < elements.size(); i++) {
            String code = toBinary(i + 1, 2);
            filter.put(elements.get(i), code);
        }

        for (int i = 0; i < elements.size(); i++) {
            String code = toBinary(i + 1, 2);
            Assertions.assertEquals(code, filter.lookUp(elements.get(i)));
        }
    }

    public static String toBinary(int x, int len) {
        return String.format("%" + len + "s",
                Integer.toBinaryString(x)).replaceAll(" ", "0");
    }

}