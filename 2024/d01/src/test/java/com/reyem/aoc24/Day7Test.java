package com.reyem.aoc24;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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
    @Test
     void testPuzzle1() throws Exception{
        long result = Day7.solvePuzzle1("day7/testinput.txt");
        assertEquals(3749, result);
    }

    @Test
    void testPuzzle2() throws Exception{
        long result = Day7.solvePuzzle2("day7/testinput.txt");
        assertEquals(11387, result);
    }

    @Test
    void couldBeTrue() throws IOException{
        BufferedReader reader = Day7.getPuzzleInput("day7/testinput.txt");
        List<Day7.Equation> equationList = Day7.parseEquations(reader);

        assertTrue(equationList.get(0).couldBeTrue(Day7.Equation.OPERATORS_1));
        assertTrue(equationList.get(1).couldBeTrue(Day7.Equation.OPERATORS_1));
        assertFalse(equationList.get(2).couldBeTrue(Day7.Equation.OPERATORS_1));
        assertFalse(equationList.get(3).couldBeTrue(Day7.Equation.OPERATORS_1));


    }

    @Test
    void calcPermutations() {
        System.out.println(" base 2");

        ArrayList<ArrayList<Character>> result = Day7.Equation.calcAllPositions(Day7.Equation.OPERATORS_1, 4);
        for(ArrayList<Character> r : result) {
            System.out.println(r.toString());
        }

        System.out.println(" base 3");
        result = Day7.Equation.calcAllPositions(Day7.Equation.OPERATORS_2, 4);
        for(ArrayList<Character> r : result) {
            System.out.println(r.toString());
        }
    }

}