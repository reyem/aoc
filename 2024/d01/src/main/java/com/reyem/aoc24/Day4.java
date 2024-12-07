package com.reyem.aoc24;

import java.io.*;
import java.util.ArrayList;

public class Day4 {

    private static final String PUZZLE_INPUT = "day4/input.txt";

    public static final String XMAS = "XMAS";

    public static final String MAS = "MAS";


    public static void main(String[] args) throws IOException {
        char[][] input = readPuzzle(PUZZLE_INPUT);
        int result1 = findXmas(input);
        System.out.println("There are " + result1 + " XMAS in the puzzle.");
        int result2 = findMas(input);
        System.out.println("There are " + result2 + " MAS over X in the puzzle.");
    }

    public static char[][] readPuzzle(String file) throws IOException {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(Day1.class.getClassLoader().getResourceAsStream(file)));
        String line = null;
        ArrayList<char[]> puzzle = new ArrayList<char[]>();
        while((line = reader.readLine()) != null){
            puzzle.add(line.toCharArray());
        };

        return puzzle.toArray(new char[0][]);
    }

    public static boolean xmasForward(char[][] puzzle, int i, int j) {
        if(j + 1 < 0) return false;
        if(j < 0 || j > puzzle[i].length - XMAS.length()) return false; // not possible to overflow right
        if(puzzle[i][j + 1 ] != 'M') return false;
        if(puzzle[i][j + 2 ] != 'A') return false;
        if(puzzle[i][j + 3 ] != 'S') return false;
        return true;
    }

    public static boolean xmasBackward(char[][] puzzle, int i, int j) {
        if(j -1 > puzzle[i].length - 1) return false;
        if(j < XMAS.length() - 1 || j > puzzle.length) return false; // not possible to overflow right
        if(puzzle[i][j - 1 ] != 'M') return false;
        if(puzzle[i][j - 2 ] != 'A') return false;
        if(puzzle[i][j - 3 ] != 'S') return false;
        return true;
    }

    public static boolean xmasUpward(char[][] puzzle, int i, int j) {
        if(i -1 > puzzle.length -1) return false;
        if(i < XMAS.length() - 1 || i - 1 > puzzle.length) return false; // not possible to overflow right
        if(puzzle[i - 1][j] != 'M') return false;
        if(puzzle[i - 2][j] != 'A') return false;
        if(puzzle[i - 3][j] != 'S') return false;
        return true;
    }

    public static boolean xmasDownward(char[][] puzzle, int i, int j) {
        if(i + 1 < 0) return false;
        if(i + 1 < 0 || i > puzzle.length - XMAS.length()) return false; // not possible to overflow right
        if(puzzle[i + 1][j] != 'M') return false;
        if(puzzle[i + 2][j] != 'A') return false;
        if(puzzle[i + 3][j] != 'S') return false;
        return true;
    }

    public static boolean xmasDiagonalTR(char[][] puzzle, int i, int j) {
        if(i -1 > puzzle.length -1 || j + 1 < 0) return false; // not possible to overflow right
        if(i < XMAS.length() - 1 || j > puzzle[i-1].length - XMAS.length()) return false; // not possible to overflow right

        if(puzzle[i - 1][j + 1] != 'M') return false;
        if(puzzle[i - 2][j + 2] != 'A') return false;
        if(puzzle[i - 3][j + 3] != 'S') return false;
        return true;
    }

    public static boolean xmasDiagonalBR(char[][] puzzle, int i, int j) {
        if(i + 1 < 0 || j + 1 < 0) return false;// not possible to overflow right
        if(i > puzzle.length - XMAS.length() || j > puzzle[i + 1].length - XMAS.length()) return false;
        if(puzzle[i + 1][j + 1] != 'M') return false;
        if(puzzle[i + 2][j + 2] != 'A') return false;
        if(puzzle[i + 3][j + 3] != 'S') return false;
        return true;
    }

    public static boolean xmasDiagonalTL(char[][] puzzle, int i, int j) {
        if(i < XMAS.length() - 1 || j < XMAS.length() -1 ) return false; // not possible to overflow right
        if(i -1 > puzzle.length - 1 || j -1 > puzzle[i -1].length - 1) return false;
        if(puzzle[i - 1][j - 1] != 'M') return false;
        if(puzzle[i - 2][j - 2] != 'A') return false;
        if(puzzle[i - 3][j - 3] != 'S') return false;
        return true;
    }

    public static boolean xmasDiagonalBL(char[][] puzzle, int i, int j) {
        if(i > puzzle.length - XMAS.length() || j < XMAS.length() -1) return false; // not possible to overflow right
        if(i + 1 <0  || j - 1 > puzzle[i+1].length -1) return false; // not possible to overflow right

        if(puzzle[i + 1][j - 1] != 'M') return false;
        if(puzzle[i + 2][j - 2] != 'A') return false;
        if(puzzle[i + 3][j - 3] != 'S') return false;
        return true;
    }

    public static int findXmas(char[][] puzzle) {
        int xmas = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == 'X') {
                    // look in all directions
                    if(xmasForward(puzzle, i, j)) xmas++;
                    if(xmasBackward(puzzle, i, j)) xmas++;
                    if(xmasUpward(puzzle,i, j)) xmas++;
                    if(xmasDownward(puzzle,i, j)) xmas++;
                    if(xmasDiagonalTR(puzzle,i, j)) xmas++;
                    if(xmasDiagonalBR(puzzle,i, j)) xmas++;
                    if(xmasDiagonalTL(puzzle,i, j)) xmas++;
                    if(xmasDiagonalBL(puzzle,i, j)) xmas++;
                }
            }
        }
        return xmas;
    }

    public static int findMas(char[][] puzzle) {
        int xmas = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (puzzle[i][j] == 'A') {
                    
                    if((xmasDiagonalTL(puzzle, i + 2, j + 2) || xmasDiagonalBR(puzzle, i -2, j -2)) &&
                       (xmasDiagonalTR(puzzle, i + 2, j - 2) || xmasDiagonalBL(puzzle, i -2, j + 2))) {
                        xmas++;
                    }

                }
            }
        }
        return xmas;
    }

}
