package com.luxoft.logeek.instanceOf;

import java.util.*;

public class Comparing {
    private Comparing() {
    }

    public static <T> boolean equal(T arg1, T arg2) {
        if (arg1 == arg2) return true;
        if (arg1 == null || arg2 == null) {
            return false;
        }
        if (arg1 instanceof Object[] && arg2 instanceof Object[]) {
            Object[] arr1 = (Object[]) arg1;
            Object[] arr2 = (Object[]) arg2;
            return Arrays.equals(arr1, arr2);
        }
        if (arg1 instanceof String && arg2 instanceof String) {
            return arg1.equals(arg2);
        }
        return arg1.equals(arg2);
    }

    public static boolean _equal(Object[] arg1, Object[] arg2) {
        if (arg1 == arg2) return true;
        if (arg1 == null || arg2 == null) {
            return false;
        }
        return Arrays.equals(arg1, arg2);
    }

    public static boolean _equal(String arg1, String arg2) {
        if (arg1 == arg2) return true;
        if (arg1 == null || arg2 == null) {
            return false;
        }
        return arg1.equals(arg2);
    }

    public static boolean areEqual(final Object x, final Object y) {
        if (x == y) {
            return true;
        }

        if (x == null || y == null) {
            return false;
        }

        if (x.equals(y)) {
            return true;
        }

        final Class xClass = x.getClass();
        final Class yClass = y.getClass();

        if (xClass.isArray() && yClass.isArray()) {
            if (xClass.equals(yClass)) {
                if (x instanceof boolean[]) {
                    return Arrays.equals((boolean[]) x, (boolean[]) y);
                } else if (x instanceof byte[]) {
                    return Arrays.equals((byte[]) x, (byte[]) y);
                } else if (x instanceof char[]) {
                    return Arrays.equals((char[]) x, (char[]) y);
                } else if (x instanceof short[]) {
                    return Arrays.equals((short[]) x, (short[]) y);
                } else if (x instanceof int[]) {
                    return Arrays.equals((int[]) x, (int[]) y);
                } else if (x instanceof long[]) {
                    return Arrays.equals((long[]) x, (long[]) y);
                } else if (x instanceof double[]) {
                    return Arrays.equals((double[]) x, (double[]) y);
                } else if (x instanceof float[]) {
                    return Arrays.equals((float[]) x, (float[]) y);
                }
            }
            return Arrays.equals((Object[]) x, (Object[]) y);
        }

        return false;
    }


    public static boolean _areEqual(float[] x, float[] y) {
        if (x == y) {
            return true;
        }

        if (x == null || y == null) {
            return false;
        }

        return Arrays.equals(x, y);
    }
}
