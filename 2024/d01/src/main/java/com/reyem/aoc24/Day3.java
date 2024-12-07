package com.reyem.aoc24;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {

    private static final String SAMPLE_INPUT = "day3/sample_input.txt";
    private static final String PUZZLE_INPUT = "day3/input.txt";

    private static char M = 'm';
    private static char U = 'u';
    private static char L = 'l';
    private static char OB = '(';
    private static char CB = ')';
    private static char CM = ',';

    private static char D = 'd';
    private static char O = 'o';
    private static char N = 'n';
    private static char AP = '\'';
    private static char T = 't';

    public static void main(String[] args) throws IOException {
        int result = solvePuzzle1(getPuzzleInput());
        System.out.println("Result Puzzle 1 is " + result);

        int result2 = solvePuzzle2(getPuzzleInput());
        System.out.println("Result Puzzle 2 is " + result2);
    }

    public static class Mul {
        public boolean enabled = true;
        public int multiplier;
        public int multiplicand;

        public Mul(int multiplier, int multiplicand, boolean enabled) {
            this.multiplier = multiplier;
            this.multiplicand = multiplicand;
            this.enabled = enabled;
        }

        public int calc() {
            return multiplier * multiplicand;
        }
    }

    private static Reader getPuzzleInput() {
        return new BufferedReader(new InputStreamReader(Day1.class.getClassLoader().getResourceAsStream(PUZZLE_INPUT)));
    }

    private enum State {
        NOP,
        MUL,
        MULTIPLIER,
        MULTIPLICAND,
        DO_OR_DONT
    }

    public static List<Mul> parse(Reader input) throws IOException {
        int in;
        List<Mul> result = new ArrayList<>();

        Deque<Character> mulStack = new ArrayDeque<>();
        Deque<Character> multiplierStack = new ArrayDeque<>();
        Deque<Character> multiplicandStack = new ArrayDeque<>();
        Deque<Character> doOrDontStack = new ArrayDeque<>();
        boolean doit = true;
        State state = State.NOP;
        // state machine nop -> mul -
        while((in = input.read()) != -1){
            char c = (char)in;
            switch (state) {
                case NOP:
                    if(c == M) {
                        mulStack.clear();
                        mulStack.push(c);
                        state = State.MUL;
                    }else if(c == D) {
                        doOrDontStack.clear();
                        doOrDontStack.push(c);
                        state = State.DO_OR_DONT;
                    }
                    break;
                case MUL:
                    if(c == U) {
                        if(mulStack.peek() == M) {
                            mulStack.push(c);
                        }else {
                            state = State.NOP;
                        }
                    }else if(c == L) {
                        if(mulStack.peek() == U) {
                            mulStack.push(c);
                        }else {
                            state = State.NOP;
                        }
                    }else if(c == OB) {
                        if(mulStack.peek() == L) {
                            mulStack.push(c);
                            state = State.MULTIPLIER;
                        }else {
                            state = State.NOP;
                        }
                    }else {
                        state = State.NOP;
                    }
                    break;
                case MULTIPLIER:
                    if(c >= '0' && c <= '9') {
                        multiplierStack.add(c);
                    }else if(c == CM) {
                        if(multiplierStack.getLast()  >= '0' && multiplierStack.getLast() <= '9') {
                            state = State.MULTIPLICAND;
                        }else {
                            multiplierStack.clear();
                            state = State.NOP;
                        }
                    }else {
                        multiplierStack.clear();
                        state = State.NOP;
                    }
                    break;
                case MULTIPLICAND:
                    if(c >= '0' && c <= '9') {
                        multiplicandStack.add(c);
                    }else if(c == CB) {
                        if(multiplicandStack.getLast()  >= '0' && multiplicandStack.getLast() <= '9') {
                           String multiplierStr = multiplierStack.stream().map(String::valueOf).collect(Collectors.joining());
                           int multiplier = Integer.parseInt(multiplierStr);

                           String multiplicandStr = multiplicandStack.stream().map(String::valueOf).collect(Collectors.joining());
                           int multiplicand = Integer.parseInt(multiplicandStr);

                           Mul mul = new Mul(multiplier, multiplicand, doit);
                           result.add(mul);
                           multiplicandStack.clear();
                           multiplierStack.clear();
                           mulStack.clear();
                           state = State.NOP;

                        }else {
                            multiplicandStack.clear();
                            multiplierStack.clear();
                            state = State.NOP;
                        }
                    }else {
                        multiplicandStack.clear();
                        multiplierStack.clear();
                        state = State.NOP;
                    }
                    break;
                case DO_OR_DONT:
                    if(doOrDontStack.getLast() == D && c == O) {
                        doOrDontStack.add(c);
                    }else if(c == N && doOrDontStack.getLast() == O) {
                        doOrDontStack.add(c);
                    }else if(c == AP && doOrDontStack.getLast() == N) {
                        doOrDontStack.add(c);
                    }else if(c == T && doOrDontStack.getLast() == AP) {
                        doOrDontStack.add(c);
                    }else if(c == OB && doOrDontStack.getLast() == T || doOrDontStack.getLast() == O) {
                        doOrDontStack.add(c);
                    }else if(c == CB && doOrDontStack.getLast() == OB) {
                        doOrDontStack.add(c);
                        String doOrDont = doOrDontStack.stream().map(String::valueOf).collect(Collectors.joining());
                        if("don't()".equalsIgnoreCase(doOrDont)){
                            doit = false;
                        }else if("do()".equalsIgnoreCase(doOrDont)){
                            doit = true;
                        }else {
                            throw new IllegalStateException("state for do or dont impossible: " + doOrDont);
                        }
                        doOrDontStack.clear();
                        state = State.NOP;
                    }else {
                        doOrDontStack.clear();
                        state = State.NOP;
                    }
                    break;
            }
        }
        return result;
    }

    public static int solvePuzzle1(Reader input) throws IOException {
        List<Mul> muls = parse(input);
        int sum = 0;
        for(Mul mul : muls) {
            sum += mul.calc();
        }
        return sum;
    }

    public static int solvePuzzle2(Reader input) throws IOException {
        List<Mul> muls = parse(input);
        int sum = 0;
        for(Mul mul : muls) {
            if(mul.enabled) {
                sum += mul.calc();
            }
        }
        return sum;
    }
}
