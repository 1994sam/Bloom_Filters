package com.project2.bloomfilter;

import com.project2.elements.Element;
import com.project2.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Runs demo on bloom filter.
 */
class BloomFilterTest {

    @Test
    void Demo() throws NoSuchAlgorithmException {
        List<Element> setA = TestUtil.getElements(1000);
        BloomFilter<Element> filter = new BloomFilter<>(10000, 7);
        setA.forEach(filter::put);
        long count = setA.stream().filter(filter::contains).count();
        System.out.println("Elements of set A found: " + count);

        List<Element> setB = TestUtil.getElements(1000);
        count = setB.stream().filter(filter::contains).count();
        System.out.println("Elements of set B found: " + count);
    }

    @Test
    void Demo1() throws NoSuchAlgorithmException {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long[] values = new long[1000000];
        for (int i = 0; i < 1000000; i++) {
            List<Element> setA = TestUtil.getElements(1000);
            List<Element> setB = TestUtil.getElements(1000);
            BloomFilter<Element> filter = new BloomFilter<>(10000, 7);
            setA.forEach(filter::put);
            long count = setB.stream().filter(filter::contains).count();
            System.out.println("Elements of set B found: " + count);
            min = Math.min(count, min);
            max = Math.max(count, max);
            values[i] = count;
        }

        long median = findMedian(values, 1000000);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Median: " + median);
    }

    public static long findMedian(long[] a, int n) {
        Arrays.sort(a);
        if (n % 2 != 0)
            return a[n / 2];
        return (long) ((a[(n - 1) / 2] + a[n / 2]) / 2.0);
    }
}