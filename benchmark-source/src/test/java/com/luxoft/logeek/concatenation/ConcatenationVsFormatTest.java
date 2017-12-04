package com.luxoft.logeek.concatenation;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConcatenationVsFormatTest {

    @Test
    public void testConcatenationVsFormat() {
        String s1 = "s1";
        String s2 = "s2";

        String formatted = String.format("%s %s", s1, s2);
        String concatenated = s1 + ' ' + s2;

        assertThat(formatted, is(concatenated));
    }

    @Test
    public void testConcatenationVsFormat_bothNull() {
        String s1 = null;
        String s2 = null;

        String formatted = String.format("%s %s", s1, s2);
        String concatenated = s1 + ' ' + s2;

        assertThat(formatted, is(concatenated));
    }

    @Test
    public void testConcatenationVsFormat_firstIsNull() {
        String s1 = null;
        String s2 = "s2";

        String formatted = String.format("%s %s", s1, s2);
        String concatenated = s1 + ' ' + s2;

        assertThat(formatted, is(concatenated));
    }

    @Test
    public void testConcatenationVsFormat_secondIsNull() {
        String s1 = "s1";
        String s2 = null;

        String formatted = String.format("%s %s", s1, s2);
        String concatenated = s1 + ' ' + s2;

        assertThat(formatted, is(concatenated));
    }
}
