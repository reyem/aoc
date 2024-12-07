package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void readPuzzle() throws IOException {
        char[][] input = Day4.readPuzzle("day4/test.txt");
        assertEquals(10, input.length);
        assertEquals(10, input[0].length);

        input = Day4.readPuzzle("day4/test1.txt");
        assertEquals(11, input.length);
        assertEquals(10, input[0].length);
    }

    @Test
    void xmasForward() {
        assertTrue(Day4.xmasForward(new char[][]{{'a', 'b', 'c', 'd', 'X', 'M', 'A', 'S'}}, 0, 4));
        assertFalse(Day4.xmasForward(new char[][]{{'a', 'b', 'c', 'd', 'X', 'M', 'A'}}, 0, 4));
        assertTrue(Day4.xmasForward(new char[][]{{'a', 'd', 'X', 'M', 'A', 'S', 'd'}}, 0, 2));
        assertTrue(Day4.xmasForward(new char[][]{{ 'X', 'M', 'A', 'S', 'd'}}, 0, 0));
    }

    @Test
    void xmasBackward() {
        assertTrue(Day4.xmasBackward(new char[][]{{'a', 'b', 'c', 'd', 'S', 'A', 'M', 'X'}}, 0, 7));
        assertFalse(Day4.xmasBackward(new char[][]{{'a', 'b', 'c', 'd', 'A', 'M', 'X'}}, 0, 7));
        assertTrue(Day4.xmasBackward(new char[][]{{'a', 'd', 'S', 'A', 'M', 'X', 'd'}}, 0, 5));
        assertTrue(Day4.xmasBackward(new char[][]{{ 'S', 'A', 'M', 'X', 'd'}}, 0, 3));
        assertFalse(Day4.xmasBackward(new char[][]{{ 'A', 'M', 'X', 'd'}}, 0, 2));
    }

    @Test
    void xmasUpward() {
        assertTrue(Day4.xmasUpward(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                {'a', 'X', 'M', 'd', 'A', 'A', 'S', 'X'},
                                                {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 3, 1));

        assertTrue(Day4.xmasUpward(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                {'a', 'X', 'M', 'd', 'A', 'A', 'S', 'X'},
                                                {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 4, 2));

        assertFalse(Day4.xmasUpward(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                 {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                 {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                 {'a', 'X', 'M', 'd', 'A', 'A', 'S', 'X'},
                                                 {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 2, 3));

    }

    @Test
    void xmasDownward() {
        assertTrue(Day4.xmasDownward(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                {'a', 'X', 'M', 'd', 'A', 'A', 'S', 'X'},
                                                {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 0, 6));

        assertTrue(Day4.xmasDownward(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                {'a', 'X', 'M', 'd', 'A', 'A', 'S', 'X'},
                                                {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 1, 4));

        assertFalse(Day4.xmasDownward(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                {'a', 'M', 'A', 'X', 'M', 'X', 'A', 'X'},
                                                {'a', 'X', 'M', 'd', 'A', 'M', 'S', 'X'},
                                                {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 2, 5));

    }

    @Test
    void xmasDiagnoalTR() {
        assertTrue(Day4.xmasDiagonalTR(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'S', 'X', 'A', 'M', 'X'},
                                                    {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                    {'a', 'M', 'M', 'd', 'A', 'A', 'S', 'X'},
                                                    {'X', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 4, 0));

        assertTrue(Day4.xmasDiagonalTR(new char[][]{{'a', 'S', 'M', 'A', 'S', 'S', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'A', 'A', 'M', 'X'},
                                                    {'a', 'M', 'A', 'M', 'M', 'A', 'A', 'X'},
                                                    {'a', 'X', 'X', 'd', 'A', 'A', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 3, 2));

        assertFalse(Day4.xmasDiagonalTR(new char[][]{{'a', 'S', 'M', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                    {'a', 'M', 'X', 'X', 'M', 'X', 'A', 'X'},
                                                    {'a', 'X', 'M', 'd', 'A', 'M', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 2, 2));

        assertFalse(Day4.xmasDiagonalTR(new char[][]{{'a', 'S', 'M', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'A'},
                                                    {'a', 'M', 'X', 'X', 'M', 'X', 'M', 'X'},
                                                    {'a', 'X', 'M', 'd', 'A', 'X', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 3, 5));

    }

    @Test
    void xmasDiagnoalTL() {
        assertTrue(Day4.xmasDiagonalTL(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'S', 'S', 'A', 'M', 'X'},
                                                    {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                    {'a', 'M', 'M', 'd', 'A', 'A', 'M', 'X'},
                                                    {'X', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 4, 7));

        assertTrue(Day4.xmasDiagonalTL(new char[][]{{'S', 'S', 'M', 'A', 'S', 'S', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'A', 'A', 'M', 'X'},
                                                    {'a', 'M', 'M', 'M', 'M', 'A', 'A', 'X'},
                                                    {'a', 'X', 'X', 'X', 'A', 'A', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 3, 3));

        assertFalse(Day4.xmasDiagonalTL(new char[][]{{'A', 'S', 'M', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'M', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                    {'a', 'M', 'X', 'X', 'M', 'X', 'A', 'X'},
                                                    {'a', 'X', 'M', 'd', 'A', 'M', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 2, 2));

        assertFalse(Day4.xmasDiagonalTL(new char[][]{{'a', 'S', 'M', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'M', 'A', 'M', 'A'},
                                                    {'a', 'M', 'X', 'X', 'M', 'X', 'M', 'X'},
                                                    {'a', 'X', 'M', 'd', 'A', 'X', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 2, 5));

        assertFalse(Day4.xmasDiagonalTL(new char[][]{{'a', 'S', 'M', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'M', 'A', 'M', 'A'},
                                                    {'A', 'M', 'X', 'X', 'M', 'X', 'M', 'X'},
                                                    {'a', 'M', 'M', 'd', 'A', 'X', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 4, 2));

    }

    @Test
    void xmasDiagnoalBR() {
        assertTrue(Day4.xmasDiagonalBR(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'S', 'X', 'A', 'M', 'X'},
                                                    {'a', 'M', 'A', 'X', 'M', 'M', 'A', 'X'},
                                                    {'a', 'M', 'M', 'd', 'A', 'A', 'A', 'X'},
                                                    {'X', 'b', 'X', 'd', 'S', 'A', 'M', 'S'}
        }, 1, 4));

        assertTrue(Day4.xmasDiagonalBR(new char[][]{{'a', 'S', 'X', 'A', 'S', 'S', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'A', 'A', 'M', 'X'},
                                                    {'a', 'M', 'A', 'M', 'A', 'A', 'A', 'X'},
                                                    {'a', 'X', 'X', 'd', 'A', 'S', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 0, 2));

        assertFalse(Day4.xmasDiagonalBR(new char[][]{{'a', 'S', 'M', 'A', 'A', 'X', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                    {'a', 'M', 'X', 'X', 'M', 'X', 'A', 'A'},
                                                    {'a', 'X', 'M', 'd', 'A', 'M', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 0, 5));

        assertFalse(Day4.xmasDiagonalBR(new char[][]{{'a', 'S', 'M', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'A'},
                                                    {'a', 'M', 'X', 'X', 'M', 'X', 'M', 'X'},
                                                    {'a', 'X', 'M', 'M', 'A', 'X', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'A', 'A', 'M', 'X'}
        }, 2, 2));

    }

    @Test
    void xmasDiagnoalBL() {
        assertTrue(Day4.xmasDiagonalBL(new char[][]{{'a', 'S', 'M', 'A', 'S', 'A', 'X', 'X'},
                                                    {'a', 'A', 'S', 'S', 'X', 'A', 'M', 'X'},
                                                    {'a', 'M', 'A', 'X', 'M', 'A', 'A', 'X'},
                                                    {'a', 'M', 'M', 'd', 'S', 'A', 'A', 'X'},
                                                    {'X', 'b', 'X', 'd', 'S', 'A', 'M', 'S'}
        }, 0, 7));

        assertTrue(Day4.xmasDiagonalBL(new char[][]{{'a', 'S', 'X', 'A', 'S', 'S', 'X', 'X'},
                                                    {'a', 'A', 'S', 'X', 'A', 'A', 'M', 'X'},
                                                    {'a', 'M', 'M', 'M', 'A', 'A', 'A', 'X'},
                                                    {'a', 'A', 'X', 'd', 'A', 'S', 'S', 'X'},
                                                    {'S', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 1, 3));

        assertFalse(Day4.xmasDiagonalBL(new char[][]{{'a', 'S', 'M', 'A', 'A', 'X', 'X', 'X'},
                                                {'a', 'A', 'S', 'M', 'X', 'A', 'M', 'X'},
                                                {'a', 'M', 'X', 'X', 'M', 'X', 'A', 'A'},
                                                {'a', 'M', 'M', 'd', 'A', 'M', 'S', 'X'},
                                                {'A', 'b', 'X', 'd', 'S', 'A', 'M', 'X'}
        }, 2, 2));

        assertFalse(Day4.xmasDiagonalBL(new char[][]{{'a', 'X', 'X', 'A', 'A', 'A', 'X', 'X'},
                                                    {'a', 'M', 'S', 'M', 'X', 'A', 'M', 'A'},
                                                    {'A', 'M', 'X', 'X', 'M', 'X', 'M', 'X'},
                                                    {'a', 'X', 'M', 'M', 'A', 'X', 'S', 'X'},
                                                    {'a', 'b', 'X', 'd', 'A', 'A', 'M', 'X'}
        }, 0, 2));

    }

    @Test
    void findXmas() throws IOException {
        char[][] puzzleTest = Day4.readPuzzle("day4/test.txt");
        int result = Day4.findXmas(puzzleTest);
        assertEquals(18, result);
    }

    @Test
    void findMas() throws IOException {
        char[][] puzzleTest = Day4.readPuzzle("day4/testMas.txt");
        int result = Day4.findMas(puzzleTest);
        assertEquals(9, result);
    }
}