package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @org.junit.jupiter.api.Test
    void solvePuzzle1() {
    }

    @org.junit.jupiter.api.Test
    void solvePuzzle2() {
    }

    @Test
    void isSafe() {
        int[] vector = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> v = Arrays.stream(vector).boxed().collect(Collectors.toList());
        assertTrue(Day2.isSafe(v, false));

        int[] vector2 = new int[]{1, 2, 3, 4, 5, 6, 2, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> v2 = Arrays.stream(vector2).boxed().collect(Collectors.toList());
        assertTrue(Day2.isSafe(v2, true));

        int[] vector3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 2};
        List<Integer> v3 = Arrays.stream(vector3).boxed().collect(Collectors.toList());
        assertTrue(Day2.isSafe(v3, true));

        int[] vector4 = new int[]{4, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> v4 = Arrays.stream(vector4).boxed().collect(Collectors.toList());
        assertTrue(Day2.isSafe(v4, true));

        int[] vector5 = new int[]{10, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> v5 = Arrays.stream(vector5).boxed().collect(Collectors.toList());
        assertTrue(Day2.isSafe(v5, true));
    }
}