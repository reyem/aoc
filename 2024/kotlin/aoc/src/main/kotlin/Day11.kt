package org.example.day11

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    println("Day 11 Part 1: ${solvePuzzleOne("/day11/input.txt")}")
    println("Day 11 Part 2: ${solvePuzzleTwo("/day11/input.txt")}")
}


fun readBlocks(filename: String): MutableList<Long> {
    object {}.javaClass.getResourceAsStream(filename)?.let { InputStreamReader(it) }?.let {
        BufferedReader(it).use { br ->
        val line = br.readLine()
        return line.split(" ").map { it.toLong() }.toMutableList()
    }
    }
    throw IllegalStateException("Cannot read input")
}

fun solvePuzzleOne(filename: String): Long {
    val blocks = readBlocks(filename)
    val cache = mutableMapOf<Long, MutableMap<Long, Long>>()
    var result = 0L
    for (block in blocks) {
        result += processBlockTimesLong(block, 25, cache)
    }
    return result
}

fun solvePuzzleTwo(filename: String): Long {
    val blocks = readBlocks(filename)
    val cache = mutableMapOf<Long, MutableMap<Long, Long>>()
    var result = 0L
    for (block in blocks) {
        result += processBlockTimesLong(block, 75L, cache)
    }
    return result
}


fun processBlockTimesLong(block: Long, times: Long, cache: MutableMap<Long, MutableMap<Long, Long>>): Long {
    var entry = cache[block]
    if (entry != null) {
        if (entry.containsKey(times)) {
            return entry.getValue(times)
        }
    } else {
        entry = mutableMapOf<Long, Long>()
        cache[block] = entry
    }
    if (times == 0L) {
        return 1L
    }
    val result = if (block == 0L) {
        processBlockTimesLong(1, times - 1, cache)
    } else if (block.toString().length % 2 == 0) {
        val blockStr = block.toString()
        processBlockTimesLong(blockStr.substring(0, blockStr.length / 2).toLong(), times - 1, cache) +
                processBlockTimesLong(
                    blockStr.substring(blockStr.length / 2, blockStr.length).toLong(),
                    times - 1,
                    cache
                )
    } else {
        processBlockTimesLong(block * 2024L, times - 1, cache)
    }
    entry[times] = result
    return result
}