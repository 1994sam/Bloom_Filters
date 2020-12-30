package com.project2.elements;

/**
 * Bean class to store element details.
 */
public class Element {

    /**
     * Stores the destination address
     */
    private final String destinationAddress;

    public Element(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    @Override
    public String toString() {
        return destinationAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return destinationAddress.equals(element.destinationAddress);
    }

    @Override
    public int hashCode() {
        return destinationAddress.hashCode();
    }
}
