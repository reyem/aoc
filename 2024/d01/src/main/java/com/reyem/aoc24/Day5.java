package com.reyem.aoc24;

import java.io.*;
import java.util.*;

public class Day5 {

    private static final String PUZZLE_INPUT = "day5/input.txt";

    public static BufferedReader getPuzzleInput(String file) {
        return new BufferedReader(new InputStreamReader(Day5.class.getClassLoader().getResourceAsStream(file)));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = getPuzzleInput(PUZZLE_INPUT);
        int result = solvePuzzle1(reader);
        System.out.println("Day 5 puzzle 1 result: " + result);
        reader.close();
        reader = getPuzzleInput(PUZZLE_INPUT);
        result = solvePuzzle2(reader);
        System.out.println("Day 5 puzzle 2 result: " + result);
    }

    public static class Rule  {
        public final int before;
        public final int after;

        public Rule(int before, int after) {
            this.before = before;
            this.after = after;
        }



    }

    public static class LineValidator implements Comparator<Integer>{
        Map<Integer, List<Rule>> rules;

        public LineValidator(List<Rule> parsedRules) {
            this.rules = new HashMap<>();
            for(Rule rule : parsedRules) {
                List<Rule> ruleList = rules.get(rule.before);
                if(ruleList == null) {
                    ruleList = new ArrayList<>();
                    rules.put(rule.before, ruleList);
                }
                ruleList.add( rule);
            }
        }

        public boolean validate(Integer[] line) {
            for(int i = 0; i < line.length - 1; i++) {
                if(compare(line[i], line[i + 1]) > 0) {
                    return false;
                }
            }
            return true;
        }

        private Integer[] parse(String line) {
            String[] parts  = line.split(",");
            return Arrays.stream(parts).map(Integer::parseInt).toArray(Integer[]::new);
        }

        public int validateAndReturnMiddle(String line) {
            Integer[] pages = parse(line);
            if(validate(pages)) {
                return pages[(pages.length / 2)].intValue();
            }else {
                return -1;
            }
        }

        public int fixWrongAndReturnMiddle(String line) {
            Integer[] pages = parse(line);
            if(validate(pages)) {
                return -1;
            }else {
                Arrays.sort(pages, this);
                return pages[(pages.length / 2)].intValue();
            }
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            List<Rule> ruleList = rules.get(o1);
            if(o1.intValue() == o2.intValue()) {
                return 0;
            }
            if(ruleList != null) {
                for(Rule rule : ruleList) {
                    if( rule.after == o2.intValue()) {
                        return -1;
                    }
                }
            }
            ruleList = rules.get(o2);
            if(ruleList != null) {
                for(Rule rule : ruleList) {
                    if( rule.after == o1.intValue()) {
                        return 1;
                    }
                }
            }
            return 0;
        }
    }

    public static LineValidator parseRules(BufferedReader reader) throws IOException {
        List<Rule> rules = new ArrayList<>();
        String line  = null;
        while (!(line = reader.readLine()).equals("")) {
            String[] parts = line.split("\\|");
            rules.add(new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        return new LineValidator(rules);
    }

    public static int solvePuzzle1(BufferedReader reader) throws IOException {
        LineValidator validator = parseRules(reader);
        String line = null;
        int sum = 0;
        while((line = reader.readLine()) != null) {
            int result = validator.validateAndReturnMiddle(line);
            if(result != -1) {
                sum += result;
            }
        }
        return sum;
    }

    public static int solvePuzzle2(BufferedReader reader) throws IOException {
        LineValidator validator = parseRules(reader);
        String line = null;
        int sum = 0;
        while((line = reader.readLine()) != null) {
            int result = validator.fixWrongAndReturnMiddle(line);
            if(result != -1) {
                sum += result;
            }
        }
        return sum;
    }
}
