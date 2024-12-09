package com.reyem.aoc24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    private static final String PUZZLE_INPUT = "day7/input.txt";

    public static void main(String[] args) {

    }

    public static BufferedReader getPuzzleInput(String file) {
        return new BufferedReader(new InputStreamReader(Day1.class.getClassLoader().getResourceAsStream(file)));
    }

    public static List<Equation> parseEquations(BufferedReader reader) throws IOException {
        List<Equation> result = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            int eqResult = Integer.parseInt(parts[0]);
            String[] rightParts = parts[1].trim().split(" ");
            int[] numberParts = Arrays.stream(rightParts).mapToInt(Integer::parseInt).toArray();
            Equation eq = new Equation(eqResult, numberParts);
            result.add(eq);
        }
        return result;
    }

    public static class Equation{
        public int result;

        public int[] parts;

        public static final char[] operators = new char[]{'*', '+'};

        public Equation(int result, int[] parts) {
            this.result = result;
            this.parts = parts;
        }

        public boolean couldBeTrue(int[] values) {
               char[] equations = new char[(int)Math.pow(operators.length, values.length - 1)];
               for(int i = 0; i < equations.length -1; i++) {
                   equations[i]
               }
        }

        public int[] calcAllResults() {
            int[] results = new int[(int)Math.pow(operators.length, parts.length - 1)];
            for(int i = 0; i< results.length; i++) {
                // ddd
            }
            return results;
        }

        public int calcAdd(int[] values){
            if(values.length == 2) {
                return values[0] + values[1];
            }else {
                return values[0] + calcAdd(Arrays.copyOfRange(values, 1, values.length));
            }
        }

        public int calcMultiply(int[] values){
            if(values.length == 2) {
                return values[0] * values[1];
            }else {
                return values[0] * calcMultiply(Arrays.copyOfRange(values, 1, values.length));
            }
        }
    }

}
