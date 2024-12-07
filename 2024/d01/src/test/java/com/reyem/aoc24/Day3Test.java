package com.reyem.aoc24;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void parse() throws IOException {
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n";
        List<Day3.Mul> result = Day3.parse(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        assertEquals(4, result.size());
    }

    @Test
    void solvePuzzle1() throws IOException {
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n";
        int sum  = Day3.solvePuzzle1(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));

        assertEquals(161, sum);

        String input2 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(51,5)+mul(32,64]then(mul(112,8)mul(8,5))\n";
        int sum2  = Day3.solvePuzzle1(new InputStreamReader(new ByteArrayInputStream(input2.getBytes())));

        assertEquals(1199, sum2);

    }

    @Test
    void solvePuzzle2() throws IOException {
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n";
        int sum  = Day3.solvePuzzle2(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));

        assertEquals(161, sum);

        String input2 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(51,5)+mul(32,64]then(mul(112,8)mul(8,5))\n";
        int sum2  = Day3.solvePuzzle2(new InputStreamReader(new ByteArrayInputStream(input2.getBytes())));

        assertEquals(1199, sum2);

        String input3 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))\n";
        int sum3  = Day3.solvePuzzle2(new InputStreamReader(new ByteArrayInputStream(input3.getBytes())));

        assertEquals(48, sum3);

    }
}