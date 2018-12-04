package com.luxoft.logeek.utils;

import com.jdk.Arrays;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test methods overridden in {@link Arrays.ArrayList} for better performance
 */
public class ArraysTest {
    private List<Integer> customList;
    private List<Integer> originList;

    @Before
    public void setUp() {
        customList = Arrays.asList(1, 2, 2, 3);
        originList = Arrays.asList(1, 2, 2, 3);
    }

    @Test
    public void lastIndexOf() {
        int index1 = customList.lastIndexOf(2);
        int index2 = originList.lastIndexOf(2);

        assertEquals(index1, index2);
    }

    @Test
    public void removeRange() {
        try {
            originList.subList(0, 2).clear();
        } catch (UnsupportedOperationException e) {
            try {
                customList.subList(0, 2).clear();
            } catch (UnsupportedOperationException e1) {
                return;
            }
        }

        throw new AssertionError("UnsupportedOperationException must be thrown");
    }

    @Test
    public void _hashCode() {
        int index1 = customList.hashCode();
        int index2 = originList.hashCode();

        assertEquals(index1, index2);
    }

    @Test
    public void toArray() {
        Object[] array1 = customList.toArray();
        Object[] array2 = originList.toArray();

        assertArrayEquals(array1, array2);
    }

}