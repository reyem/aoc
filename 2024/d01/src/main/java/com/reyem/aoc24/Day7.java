package com.reyem.aoc24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    private static final String PUZZLE_INPUT = "day7/input.txt";

    public static void main(String[] args) throws IOException {
        long puzzle1 = solvePuzzle1(PUZZLE_INPUT);
        System.out.println("Puzzle 1 is: " + puzzle1);

        long puzzle2 = solvePuzzle2(PUZZLE_INPUT);
        System.out.println("Puzzle 2 is: " + puzzle2);
    }

    public static long solvePuzzle1(String input) throws IOException {
        BufferedReader reader = getPuzzleInput(input);
        List<Equation> equations = parseEquations(reader);
        long result = 0;
        for(Equation eq : equations) {
            if(eq.couldBeTrue(Day7.Equation.OPERATORS_1)) result += eq.result;
        }
        return result;
    }

    public static long solvePuzzle2(String input) throws IOException {
        BufferedReader reader = getPuzzleInput(input);
        List<Equation> equations = parseEquations(reader);
        long result = 0;
        for(Equation eq : equations) {
            if(eq.couldBeTrue(Day7.Equation.OPERATORS_2)) result += eq.result;
        }
        return result;
    }

    public static BufferedReader getPuzzleInput(String file) {
        return new BufferedReader(new InputStreamReader(Day1.class.getClassLoader().getResourceAsStream(file)));
    }

    public static List<Equation> parseEquations(BufferedReader reader) throws IOException {
        List<Equation> result = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            long eqResult = Long.parseLong(parts[0]);
            String[] rightParts = parts[1].trim().split(" ");
            int[] numberParts = Arrays.stream(rightParts).mapToInt(Integer::parseInt).toArray();
            Equation eq = new Equation(eqResult, numberParts);
            result.add(eq);
        }
        return result;
    }

    public static class Equation{
        public long result;

        public int[] parts;

        public static final char[] OPERATORS_1 = new char[]{'*', '+'};
        public static final char[] OPERATORS_2 = new char[]{'*', '+', '|'};

        public Equation(long result, int[] parts) {
            this.result = result;
            this.parts = parts;
        }

        public boolean couldBeTrue(char[] operators) {
            ArrayList<ArrayList<Character>> allCombinations = calcAllPositions(operators, parts.length -1);
            for(ArrayList<Character> eq : allCombinations) {
                long left = parts[0];
                for(int i = 1; i < parts.length; i++) {
                    char op = eq.get(i-1);
                    int right = parts[i];
                    if(op == '*') {
                        left *= right;
                    }else if(op == '+'){
                        left += right;
                    }else if(op == '|') {
                        left = Long.parseLong(String.valueOf(left) + String.valueOf(right));
                    }
                }
                if(left == result) return true;
            }
            return false;
        }

        public static ArrayList<ArrayList<Character>> calcAllPositions(char[] operators, int n) {
            int base = operators.length;
            int k = (int) Math.pow(base, n); // binary 0 - k
            ArrayList<ArrayList<Character>> result = new ArrayList<>();
            for(int i = 0; i < k; i++) {
                //0 == +
                //1 == -
                int s = i;
                ArrayList<Character> comb = new ArrayList<>();
                for(int p = 0; p < n; p++) {// TODO operators shift if not base 2??

                    int r = s % base;
                    s = s / base;
                    if(r == 0) {
                        comb.add('+');
                    }else if(r == 1) {
                        comb.add('*');
                    }else if(r == 2) {
                        comb.add('|');
                    }
                    /*if((s & 0x1) == 1) {
                        comb.add('*');
                    }else {
                        comb.add('+');
                    }
                    s = s >> 1;*/
                }
                result.add(comb);
            }
            return result;
        }

    }

}
