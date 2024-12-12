package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {

    @Test
    void testPuzzle1() throws Exception{
        int result = Day8.solvePuzzle1("day8/testinput.txt");
        assertEquals(14, result);
    }

    @Test
    void testPuzzle2() throws Exception{
        int result = Day8.solvePuzzle2("day8/testinput.txt");
        assertEquals(34, result);
    }
}
