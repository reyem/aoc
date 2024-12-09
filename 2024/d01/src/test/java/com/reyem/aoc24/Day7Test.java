package com.reyem.aoc24;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


class Day7Test {

    @Test
    void getPuzzleInput() throws IOException {
        BufferedReader reader = Day7.getPuzzleInput("day7/testinput.txt");
        List<Day7.Equation> equationList = Day7.parseEquations(reader);
        assertEquals(9, equationList.size());
        assertEquals(190, equationList.get(0).result);
        assertEquals(2, equationList.get(0).parts.length);
        assertEquals(10, equationList.get(0).parts[0]);
        assertEquals(19, equationList.get(0).parts[1]);

        assertEquals(3267, equationList.get(1).result);
        assertEquals(3, equationList.get(1).parts.length);
        assertEquals(81, equationList.get(1).parts[0]);
        assertEquals(40, equationList.get(1).parts[1]);
        assertEquals(27, equationList.get(1).parts[2]);

    }

}