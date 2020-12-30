package com.project2.bloomfilter;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implements the Bloom filter.
 *
 * @param <T>
 */
public class BloomFilter<T> {
    private final boolean[] bits;
    private final int size;
    private final int numberOfHashFunctions;
    private final MessageDigest messageDigest;

    public BloomFilter(int size, int numberOfHashFunctions) throws NoSuchAlgorithmException {
        this.size = size;
        this.numberOfHashFunctions = numberOfHashFunctions;
        bits = new boolean[size];
        messageDigest = MessageDigest.getInstance("SHA-512");
    }

    /**
     * Checks if the {@code element} is present in the bloom filter.
     *
     * @param element the element to be searched
     * @return true if the element is present.
     */
    public boolean contains(final T element) {
        int functionNumber = 1;
        while (functionNumber <= numberOfHashFunctions) {
            int hash = hashFunction(element, functionNumber++);
            if (hash > 0 && !bits[hash]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds the element to the bloom filter.
     *
     * @param element the element to be added.
     */
    public void put(T element) {
        for (int i = 1; i <= numberOfHashFunctions; i++) {
            int i1 = hashFunction(element, i);
            int count = 0;
            while (i1 < 0 && count < 1000) {
                i1 = hashFunction(element, i);
                count++;
            }
            if(i1 >= 0) {
                bits[i1] = true;
            }
        }
    }

    /**
     * The function which calculates the hash value to add the element to the hash table. This function creates a
     * hash value which lies from 0 to capacity of the hash table.
     *
     * @param element        the element to be inserted in the hash table.
     * @param functionNumber the hash function which is to be used.
     * @return the hash value for the element.
     */
    public int hashFunction(final T element, int functionNumber) {
        messageDigest.reset();
        byte[] bytes = ByteBuffer.allocate(4).putInt(element.hashCode()).array();
        messageDigest.update(bytes, 0, bytes.length);
        return Math.abs(new BigInteger(1, messageDigest.digest()).intValue() * (functionNumber - 1)) % (size - 1);
    }
}
