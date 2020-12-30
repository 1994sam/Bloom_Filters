package com.project2.codedbloomfilter;

import com.project2.bloomfilter.BloomFilter;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of Coded Bloom filter.
 *
 * @param <T>
 */
public class CodedBloomFilter<T> {
    private final List<BloomFilter<T>> filters;
    private final int numberOfHashes;
    private final int sizeOfBloomFilters;
    private final int numberOfBloomFilters;

    public CodedBloomFilter(int numberOfSets, int numberOfHashes, int sizeOfBloomFilters) throws NoSuchAlgorithmException {
        this.numberOfHashes = numberOfHashes;
        this.sizeOfBloomFilters = sizeOfBloomFilters;
        numberOfBloomFilters = (int) (Math.log(numberOfSets + 1) / Math.log(2));
        filters = initializeBloomFilters();
    }

    /**
     * Adds the element to the bloom filter.
     *
     * @param element the element to be added.
     * @param code    the code to be used for addition.
     */
    public void put(T element, String code) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                filters.get(i).put(element);
            }
        }
    }

    /**
     * Finds if the element is present in the Bloom filters.
     *
     * @param element the element to be found.
     * @return the code associated with the element. If the element is not present then a code with all zeros is returned.
     */
    public String lookUp(T element) {
        char[] code = new char[numberOfBloomFilters];
        Arrays.fill(code, '0');
        for (int i = 0; i < numberOfBloomFilters; i++) {
            if (filters.get(i).contains(element)) {
                code[i] = '1';
            }
        }
        return new String(code);
    }

    /**
     * @return Creates the bloom filters to be used for insertion.
     * @throws NoSuchAlgorithmException if the algorithm that is being used for hashing is not available.
     */
    private List<BloomFilter<T>> initializeBloomFilters() throws NoSuchAlgorithmException {
        List<BloomFilter<T>> list = new ArrayList<>();
        for (int i = 0; i < numberOfBloomFilters; i++) {
            BloomFilter<T> bloomFilter = new BloomFilter<>(sizeOfBloomFilters, numberOfHashes);
            list.add(bloomFilter);
        }
        return list;
    }
}
