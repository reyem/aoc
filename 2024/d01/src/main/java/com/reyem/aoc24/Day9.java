package com.reyem.aoc24;

import java.io.*;
import java.util.*;

public class Day9 {

    private static final String PUZZLE_INPUT = "day9/input.txt";

    public static void main(String[] args) throws IOException {
        long checksum = Day9.solvePuzzle1(PUZZLE_INPUT);
        System.out.println("Day 9 puzzle 1. Checksum: " + checksum);
        checksum = Day9.solvePuzzle2(PUZZLE_INPUT);
        System.out.println("Day 9 puzzle 2. Checksum: " + checksum);
    }

    public static void writeToFile(String disk, String filename) throws IOException {
        String fileName = Day9.class.getResource("/").getFile() + filename;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.write(disk.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static Reader getInput(String file) throws FileNotFoundException {
        return new InputStreamReader(Day9.class.getClassLoader().getResourceAsStream(file));
    }

    public static long solvePuzzle1(String file) throws IOException {
        Reader reader = Day9.getInput(file);
        int[] uncompressed = Day9.uncompress(reader);
        //writeToFile(uncompressed, "day9/uncompressed.txt");
        int[] compacted = Day9.compact(uncompressed);
        //writeToFile(uncompressed,"day9/compacted.txt");
        return Day9.checksum(compacted);
    }

    public static long solvePuzzle2(String file) throws IOException {
        Reader reader = Day9.getInput(file);
        int[] uncompressed = Day9.uncompress(reader);
        //writeToFile(uncompressed, "day9/uncompressed.txt");
        int[] compacted = Day9.defrag(uncompressed);
        //writeToFile(uncompressed,"day9/compacted.txt");
        return Day9.checksum(compacted);
    }

    public static int[] uncompress(Reader reader) throws IOException {
        char c;
        List<Integer> uncompressed = new ArrayList<>();
        int fileId = 0;
        boolean isFile = true;
        while((c =(char)reader.read()) != '\uFFFF'){
            int length = c - '0'; // ascii to int by subtracting ascii code of 0
            if(isFile) {
                for(int i = 0 ; i < length ; i++){
                    uncompressed.add(fileId);
                }
                fileId++;
                isFile = false;
            }else {
                for(int i = 0 ; i < length ; i++){
                    uncompressed.add(-1);
                }
                isFile = true;
            }
        };
        return uncompressed.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] compact(int[] uncompressed) {
        int lastGap = -1;
        for(int i = uncompressed.length - 1; i >= 0; i--) {
            if(uncompressed[i] != -1 && lastGap < i) {
                lastGap = swapWithLeftMostFree(uncompressed, i);
            }
        }
        return uncompressed;
    }

    public static int[] defrag(int[] uncompressed) {
        int lastGap = -1;
        for(int i = uncompressed.length - 1; i >= 0; i--) {
            if(uncompressed[i] != -1 && lastGap < i) {
                int length = getFileLength(uncompressed, i);
                lastGap = swapWithLeftMostFree(uncompressed, i, length);
                i = i - length + 1;
            }
        }
        return uncompressed;
    }

    private static int swapWithLeftMostFree(int[] uncompressed, int i, int length) {
        int startGap = -1;

        for(int j = 0 ; j < uncompressed.length && j < i ; j++){
            if(uncompressed[j] == -1){
                if(startGap == -1){
                    startGap = j;
                }
                if(j - startGap + 1 == length){
                    // found, swap it in.
                    int to = startGap;
                    for(int k = i - length + 1; k <= i; k++) {
                        uncompressed[to] = uncompressed[k];
                        uncompressed[k] = -1;
                        to++;
                    }
                    return startGap;
                }
            }else {
                startGap = -1;
            }
        }
        return startGap;
    }

    private static int getFileLength(int[] uncompressed, int i) {
        int length = 1;
        while(i > 0 && uncompressed[i -1] == uncompressed[i]) {
            length++;
            i--;
        };
        return length;
    }

    private static int swapWithLeftMostFree(int[] files, int idx) {
        for(int i = 0; i < files.length; i++) {
            if(files[i] == -1 && i < idx) {
                files[i] = files[idx];
                files[idx] = -1;
                return i;
            }
        }
        return idx;
    }

    public static long checksum(int[] compacted) {
        long checksum = 0;
        for (int i = 0; i < compacted.length; i++) {
            if(compacted[i] != -1) {
                int id = compacted[i];
                checksum += id * i;
            }
        }
        return checksum;
    }


}
