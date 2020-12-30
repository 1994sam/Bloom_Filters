# Bloom Filters

This repository has the implementation of Bloom filter, Counting Bloom filter, and Coded Bloom filter.

* Bloom filter

A Bloom filter is a space-efficient probabilistic data structure, conceived by Burton Howard Bloom in 1970, that is used to test whether an element is a member of a set. False positive matches are possible, but false negatives are not – in other words, a query returns either "possibly in set" or "definitely not in set". 

* Counting Bloom filter

A Counting Bloom filter is a generalized data structure of Bloom filter, that is used to test whether a count number of a given element is smaller than a given threshold when a sequence of elements is given. As a generalized form of Bloom filter, false positive matches are possible, but false negatives are not – in other words, a query returns either "possibly bigger or equal than the threshold" or "definitely smaller than the threshold"

* Coded Bloom filter

In coded bloom filter, the table is divided into multiple buckets which have a binary code associated with them. First we determine the bucket into which the element is to be inserted and then the rest of the functionality is similar to a bloom filter.
