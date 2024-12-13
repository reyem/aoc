package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

    @Test
    void testUncompress() throws Exception {
        Reader reader = Day9.getInput("day9/testinput.txt");
        int[] uncompressed = Day9.uncompress(reader);
        assertArrayEquals(new int[]{0,0,-1,-1,-1,1,1,1,-1,-1,-1,2,-1,-1,-1,3,3,3,-1,4,4,-1,5,5,5,5,-1,6,6,6,6,-1,7,7,7,-1,8,8,8,8,9,9}, uncompressed);
    }

    @Test
    void testCompact() throws Exception {
        Reader reader = Day9.getInput("day9/testinput.txt");
        int[] uncompressed = Day9.uncompress(reader);
        int[] compacted = Day9.compact(uncompressed);
        assertArrayEquals(new int[]{0, 0, 9, 9, 8, 1, 1, 1, 8, 8, 8, 2, 7, 7, 7, 3, 3, 3, 6, 4, 4, 6, 5, 5, 5, 5, 6, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, compacted);
    }

    @Test
    void testDefrag() throws Exception {
        Reader reader = Day9.getInput("day9/testinput.txt");
        int[] uncompressed = Day9.uncompress(reader);
        int[] defrag = Day9.defrag(uncompressed);
        assertArrayEquals(new int[]{0,0,9,9,2,1,1,1,7,7,7,-1,4,4,-1,3,3,3,-1,-1,-1,-1,5,5,5,5,-1,6,6,6,6,-1,-1,-1,-1,-1,8,8,8,8,-1,-1}, defrag);
    }

    @Test
    void testChecksumAfterDefrag() throws Exception {
        Reader reader = Day9.getInput("day9/testinput.txt");
        int[] uncompressed = Day9.uncompress(reader);
        int[] defrag = Day9.defrag(uncompressed);
        long checksum = Day9.checksum(defrag);
        assertEquals(2858, checksum);
    }

    @Test
    void testChecksum() throws Exception {
        Reader reader = Day9.getInput("day9/testinput.txt");
        int[] uncompressed = Day9.uncompress(reader);
        int[] compacted = Day9.compact(uncompressed);
        long checksum = Day9.checksum(compacted);
        assertEquals(1928, checksum);
    }
}
