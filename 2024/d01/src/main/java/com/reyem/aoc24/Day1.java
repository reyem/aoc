package com.reyem.aoc24;

import java.io.*;
import java.util.*;

public class Day1 {

    private static final String SAMPLE_INPUT = "day1/sample_input.txt";
    private static final String PUZZLE_INPUT = "day1/input.txt";

    public static void main(String[] args) {
        PuzzleInput result = getPuzzleInput();

        System.out.println("The solution for puzzle 1: " + solvePuzzle1(result.left, result.right));
        System.out.println("The solution for puzzle 2: " + solvePuzzle2(result.left, result.right));

    }

    private static PuzzleInput getPuzzleInput() {
        BufferedReader input = new BufferedReader(new InputStreamReader(Day1.class.getClassLoader().getResourceAsStream(PUZZLE_INPUT)));
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        try(input) {
            String line = input.readLine();
            do {
                String[] numbers = line.split("   ");
                left.add(Integer.parseInt(numbers[0]));
                right.add(Integer.parseInt(numbers[1]));
                line = input.readLine();
            } while(line != null);
        }catch (IOException e){
            e.printStackTrace();;
        }
        PuzzleInput result = new PuzzleInput(left, right);
        return result;
    }

    private static class PuzzleInput {
        public final ArrayList<Integer> left;
        public final ArrayList<Integer> right;

        public PuzzleInput(ArrayList<Integer> left, ArrayList<Integer> right) {
            this.left = left;
            this.right = right;
        }
    }

    public static int solvePuzzle1(ArrayList<Integer> left, ArrayList<Integer> right) {
        int result = 0;
        left.sort(null);
        right.sort(null);
        for(int i = 0; i < left.size(); i++) {
            int diff = Math.abs(left.get(i) - right.get(i));
            result += diff;
        }

        return result;
    }

    public static int solvePuzzle2(ArrayList<Integer> left, ArrayList<Integer> right) {
        int result = 0;
        left.sort(null);
        right.sort(null);
        int prevResult = 0;
        int prev = -1;
        int rightStart = 0;
        for(int i = 0; i < left.size(); i++) {
            int l = left.get(i);
            if(prev >= 0 && prev == l) {
                result += prevResult * l;
            }else {
                // count
                int count = 0;

                int r = right.get(rightStart);
                // skip to number if possible.
                boolean found = true;
                while(r != l && found) {
                    rightStart++;
                    if(rightStart < right.size()) {
                        r = right.get(rightStart);
                    }else {
                        rightStart = 0;
                        count = 0;
                        found = false;
                    }
                }
                if(found) {
                    while(r == l) {
                        count++;
                        rightStart++;
                        r = right.get(rightStart);
                    }
                    prev = l;
                    prevResult = count;
                }
                result += count * l;
            }
        }

        return result;
    }

}


