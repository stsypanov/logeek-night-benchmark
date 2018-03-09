package com.luxoft.logeek.utils;

public class ArrayUtilRt {
    /**
     * @param src source array.
     * @param o object to be found.
     * @return index of {@code obj} in the {@code src} array.
     * Returns {@code -1} if passed object isn't found. This method uses
     * {@code equals} of arrays elements to compare {@code obj} with
     * these elements.
     */
    public static <T> int find(final T[] src, final T o) {
        if (o == null) {
            for (int i = 0; i < src.length; i++)
                if (src[i] == null)
                    return i;
        } else {
            for (int i = 0; i < src.length; i++)
                if (o.equals(src[i]))
                    return i;
        }
        return -1;
    }

    /**
     * @param src source array.
     * @param obj object to be found.
     * @return index of {@code obj} in the {@code src} array.
     * Returns {@code -1} if passed object isn't found. This method uses
     * {@code equals} of arrays elements to compare {@code obj} with
     * these elements.
     */
    public static <T> int _find(final T[] src, final T obj) {
        for (int i = 0; i < src.length; i++) {
            final T o = src[i];
            if (o == null) {
                if (obj == null) {
                    return i;
                }
            } else {
                if (o.equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
