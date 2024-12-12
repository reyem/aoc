package com.reyem.aoc24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day8 {

    private static final String PUZZLE_INPUT = "day8/input.txt";

    public static void main(String[] args) {
        int puzzle1 = solvePuzzle1(PUZZLE_INPUT);
        System.out.println("Puzzle 1: " + puzzle1);

        int puzzle2 = solvePuzzle2(PUZZLE_INPUT);
        System.out.println("Puzzle 2: " + puzzle2);
    }

    public static int solvePuzzle1(String s) {
        char[][] charMap = getPuzzleInput(s);
        Map map = new Map(charMap, false);
        return map.distinctAntinodes();
    }

    public static int solvePuzzle2(String s) {
        char[][] charMap = getPuzzleInput(s);
        Map map = new Map(charMap, true);
        return map.distinctAntinodes();
    }

    public static class Map {
        HashMap<Character, List<Position>> antennaMap = new HashMap<>();

        Set<Position> antinodes = new HashSet<>();
        char[][] charMap;

        public Map(char[][] charMap, boolean harmonics) {
            this.charMap = charMap;
            readMap(charMap);
            calculateAntinodes(harmonics);
        }

        public int distinctAntinodes() {
            return antinodes.size();
        }

        private void calculateAntinodes(boolean harmonics) {
            for(char antennaType : antennaMap.keySet()) {
                List<Position> positions = antennaMap.get(antennaType);
                for(int i = 0; i < positions.size(); i++) {
                    Position posi = positions.get(i);
                    for(int j = 0; j < positions.size(); j++) {
                        if(i != j) {
                            Position posj= positions.get(j);
                            if(harmonics) {
                                int deltaX = posi.x() - posj.x();
                                int deltaY = posi.y() - posj.y();
                                double elevation = (double) deltaY / (double) deltaX;
                                Position posj2 = new Position(posj.x(), posj.y());
                                while(posj.x() >= 0 && posj.x() < charMap[0].length && posj.y() >= 0 && posj.y() < charMap.length){
                                    antinodes.add(posj);
                                    posj = new Position(posj.x() + deltaX, posj.y() + deltaY);
                                }
                                while(posj2.x() >= 0 && posj2.x() < charMap[0].length && posj2.y() >= 0 && posj2.y() < charMap.length){
                                    antinodes.add(posj2);
                                    posj2 = new Position(posj2.x() + deltaX, posj2.y() + deltaY);
                                }
                            }else {
                                int deltaX = posi.x() - posj.x();
                                int deltaY = posi.y() - posj.y();
                                Position posx1 = new Position(posi.x() + deltaX, posi.y() + deltaY);
                                Position posx2 = new Position(posj.x() - deltaX, posj.y() - deltaY);
                                if(posx1.x() >= 0 && posx1.x() < charMap[0].length && posx1.y() >= 0 && posx1.y() < charMap.length) {
                                    antinodes.add(posx1);
                                }
                                if(posx2.x() >= 0 && posx2.x() < charMap[0].length && posx2.y() >= 0 && posx2.y() < charMap.length) {
                                    antinodes.add(posx2);
                                }
                            }
                        }
                    }
                }
            }
        }

        private void readMap(char[][] charMap) {
            for(int y = 0; y < charMap.length; y++ ) {
                for(int x = 0; x < charMap.length; x++) {
                    char c = charMap[y][x];
                    if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')|| (c >= 'A' && c <= 'Z') ) {
                        if(!antennaMap.containsKey(c)){
                            antennaMap.put(c, new ArrayList<>());
                        }
                        antennaMap.get(c).add(new Position(x, y));
                    }
                }
            }
        }
    }

    public static char[][] getPuzzleInput(String file) {
        BufferedReader input = new BufferedReader(new InputStreamReader(Day8.class.getClassLoader().getResourceAsStream(file)));
        ArrayList<char[]> rows = new ArrayList<>();
        try (input) {
            String line = input.readLine();
            do {
                char[] mapRow = line.toCharArray();
                rows.add(mapRow);
                line = input.readLine();
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows.toArray(new char[0][]);
    }

    public static char[][] copyMap(char[][] map) {
        char[][] newMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            newMap[i] = Arrays.copyOf(map[i], map[i].length);
        }
        return newMap;
    }
}
