package com.project2.bloomfilter;

import com.project2.elements.Element;
import com.project2.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
     * Runs demo on counting bloom filter.
 */
class CountingBloomFilterTest {

    private CountingBloomFilter<Element> filter;
    private List<Element> elements;

    @BeforeEach
    void beforeEach() throws NoSuchAlgorithmException {
        filter = new CountingBloomFilter<>(10000, 7);
        elements = TestUtil.getElements(1000);
    }

    @Test
    void Demo() {
        elements.forEach(element -> filter.put(element));
        //remove 500 elements from the filter.
        elements.stream().limit(500).forEach(element -> filter.remove(element));
        List<Element> setB = TestUtil.getElements(500);
        //add 500 new elements to the bloom filter.
        setB.forEach(element -> filter.put(element));
        long count = elements.stream().filter(filter::contains).count();
        System.out.println("Elements of set A found: " + count);
    }

    @Test
    void put() {
        elements.forEach(element -> filter.put(element));
        for (Element element : elements) {
            Assertions.assertTrue(filter.contains(element));
        }
    }

    @Test
    void remove() {
        elements.forEach(element -> filter.put(element));
        Assertions.assertTrue(elements.stream().filter(filter::contains).count() != elements.size());
    }
}