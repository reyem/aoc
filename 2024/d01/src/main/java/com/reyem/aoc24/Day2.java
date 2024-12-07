package com.reyem.aoc24;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day2 {

    private static final String SAMPLE_INPUT = "day2/sample_input.txt";
    private static final String PUZZLE_INPUT = "day2/input.txt";


    public static void main(String[] args) {
        ArrayList<int[]> inputs = getPuzzleInput();
        System.out.println("Day2 puzzle 1 is : " + solvePuzzle1(inputs));
        System.out.println("Day2 puzzle 2 is : " + solvePuzzle2(inputs));
    }

    private static ArrayList<int[]> getPuzzleInput() {
        BufferedReader input = new BufferedReader(new InputStreamReader(Day1.class.getClassLoader().getResourceAsStream(PUZZLE_INPUT)));
        ArrayList<int[]> vectors = new ArrayList<>();
        try (input) {
            String line = input.readLine();
            do {
                String[] numbers = line.split(" ");
                int[] vector = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    vector[i] = Integer.parseInt(numbers[i]);
                }
                vectors.add(vector);
                line = input.readLine();
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
        return vectors;
    }

    public static int solvePuzzle1(ArrayList<int[]> vectors) {
        int result = 0;
        for (int[] vector : vectors) {
            List<Integer> v = Arrays.stream(vector).boxed().collect(Collectors.toList());
            if (isSafe(v, false)) {
                result++;
            }
        }
        return result;
    }

    public static int solvePuzzle2(ArrayList<int[]> vectors) {
        int result = 0;
        for (int[] vector : vectors) {
            List<Integer> v = Arrays.stream(vector).boxed().collect(Collectors.toList());
            if (isSafe(v, true)) {
                result++;
            }
        }
        return result;
    }

    public static boolean isSafe(List<Integer> v, boolean tolerate) {

        boolean increasing = v.get(1) - v.get(0) > 0;

        int j = 1;
        for (int i = 0; i < v.size() - 1; i++) {
            int diff = v.get(j) - v.get(i);
            if (increasing) {
                if (diff <= 0 || Math.abs(diff) > 3) {
                    if (!tolerate) {
                        return false;
                    } else {
                        List<Integer> v2 = new ArrayList<>(v);
                        v2.remove(i);
                        if (!isSafe(v2, false)) {
                            v2 = new ArrayList<>(v);
                            v2.remove(j);
                            if (!isSafe(v2, false)) {
                                if (i == 1) {
                                    v2 = new ArrayList<>(v);
                                    v2.remove(0);
                                    return isSafe(v2, false);
                                } else {
                                    return false;
                                }
                            }else {
                                return true;
                            }
                        } else {
                            return true;
                        }

                    }

                }
            } else {
                if (diff >= 0 || Math.abs(diff) > 3) {
                    if (!tolerate) {
                        return false;
                    } else {
                        List<Integer> v2 = new ArrayList<>(v);
                        v2.remove(i);
                        if (!isSafe(v2, false)) {
                            v2 = new ArrayList<>(v);
                            v2.remove(j);
                            if (!isSafe(v2, false)) {
                                if (i == 1) {
                                    v2 = new ArrayList<>(v);
                                    v2.remove(0);
                                    return isSafe(v2, false);
                                } else {
                                    return false;
                                }
                            } else {
                                return true;
                            }
                        }else {
                            return true;
                        }
                    }
                }
            }
            j++;
        }
        return true;
    }


}
