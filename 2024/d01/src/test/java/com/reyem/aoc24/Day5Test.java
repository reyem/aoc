package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    private static final String TEST_FIE = "day5/testinput.txt";

    @Test
    void solvePuzzle1() throws IOException {
        BufferedReader reader = Day5.getPuzzleInput(TEST_FIE);
        int solution = Day5.solvePuzzle1(reader);
        assertEquals(143, solution);
    }


    @Test
    void parseRules() throws IOException {
        BufferedReader reader = Day5.getPuzzleInput(TEST_FIE);
        Day5.LineValidator validator = Day5.parseRules(reader);
        assertTrue(validator.rules.containsKey(47));
        assertTrue(validator.rules.containsKey(97));
        assertTrue(validator.rules.containsKey(75));
        assertTrue(validator.rules.containsKey(53));

        List<Day5.Rule> rules75 = validator.rules.get(75);
        assertEquals(5, rules75.size());
        assertEquals(29, rules75.get(0).after);



    }

    @Test
    void solvePuzzle2() throws IOException {
        BufferedReader reader = Day5.getPuzzleInput(TEST_FIE);
        int solution = Day5.solvePuzzle2(reader);
        assertEquals(123, solution);
    }
}