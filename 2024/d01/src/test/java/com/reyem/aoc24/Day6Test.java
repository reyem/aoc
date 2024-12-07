package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void getPuzzleInput() {
        char[][] map = Day6.getPuzzleInput("day6/testinput.txt");
        assertEquals(10, map.length);
        assertEquals(10, map[0].length);
        assertEquals('#', map[0][4]);
        assertEquals('^', map[6][4]);
    }

    @Test
    void solvePuzzle1() {

        char[][] map = Day6.getPuzzleInput("day6/testinput.txt");
        Day6.Map solution = new Day6.Map(map);
        assertEquals(41, Day6.solvePuzzle1(solution).distinctPositions);
    }

    @Test
    void solvePuzzle2() {

        char[][] map = Day6.getPuzzleInput("day6/testinput.txt");
        Day6.Map solution = new Day6.Map(map);
        int result = Day6.solvePuzzle1(solution).positionForEndlessLoop;
        Day6.printMap(map);
        assertEquals(6,result);
    }


    @Test
    void copyMap() {
        char[][] map = Day6.getPuzzleInput("day6/testinput.txt");
        char[][] copy = Day6.copyMap(map);
        copy[0][0] = 'H';
        Day6.printMap(map);
        Day6.printMap(copy);


    }
}