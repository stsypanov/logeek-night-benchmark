package com.luxoft.logeek.sort;

import java.util.Comparator;

/**
 * Substitutes standard implementation of java.util.Arrays
 */
public class ArraysStub {

    public static void sort(Object[] a) {
        ComparableTimSort.sort(a, 0, a.length, null, 0, 0);
    }

    public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
        if (c == null) {
            sort(a, fromIndex, toIndex);
        } else {
            rangeCheck(a.length, fromIndex, toIndex);
            TimSort.sort(a, fromIndex, toIndex, c, null, 0, 0);
        }
    }

    public static void sort(Object[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        ComparableTimSort.sort(a, fromIndex, toIndex, null, 0, 0);
    }

    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }
}
