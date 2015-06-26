package com.tsystems.jschool.railage.service.helper;

/**
 * Created by rudolph on 26.06.15.
 */
public class Triple<T, K, V> {

    private T first;
    private K second;
    private V third;

    public Triple(T first, K second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public K getSecond() {
        return second;
    }

    public void setSecond(K second) {
        this.second = second;
    }

    public V getThird() {
        return third;
    }

    public void setThird(V third) {
        this.third = third;
    }
}
