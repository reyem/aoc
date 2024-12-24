package org.example

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

const val DEBUG = false

fun main() {
    println("Puzzle 1: ${solvePuzzleOne("/day10/input.txt")}")
    println("Puzzle 2: ${solvePuzzleTwo("/day10/input.txt")}")
}

fun readMap(filename: String): Array<IntArray> {
    val map = mutableListOf<IntArray>()
    object {}.javaClass.getResourceAsStream(filename)?.let { InputStreamReader(it) }?.let {
        BufferedReader(it).use { br ->
        var line = br.readLine()
        while (line != null) {
            val values = line.chars().map { it.toChar().digitToInt() }.toList().toIntArray()
            map.add(values)
            line = br.readLine()
        }

    }
    }
    return map.toTypedArray()
}

data class Point(val x: Int, val y: Int)

data class Trailhead(val pos: Point, var score: Int = 0, var paths: MutableList<PathElement> = mutableListOf<PathElement>()) {
    fun reachablePeaks() : Int {
        val distinctPaths = mutableListOf<Point>()
        paths.forEach {
            if(!distinctPaths.contains(it.hill)) {
                distinctPaths.add(it.hill)
            }
        }
        return distinctPaths.size
    }
}

data class PathElement(val pos: Point, val previous: PathElement?, val hill: Point)


fun findHills(map: Array<IntArray>): List<Point> {
    val hills = mutableListOf<Point>()
    for (y in map.indices) {
        for (x in map[y].indices) {
            val height = map[y][x]
            if (height == 9) {
                val point = Point(x, y)
                hills.add(point)
            }
        }
    }
    return hills
}

fun findPaths(map: Array<IntArray>, start: PathElement, trailheads: MutableSet<Trailhead>) {
    val points = findNext(map, start)
    val found = mutableListOf<Trailhead>()
    points.forEachIndexed { index, pathE ->
        val point = pathE.pos
        if(map[point.y][point.x] == 0) {
            val th = trailheads.find { it.pos == point }
            th?.also {
                if(!found.contains(it)){
                    it.score.also { th.score = it.plus(1);}
                    it.paths.add(pathE)
                    found.add(it)
                }
            }
        }else {
            findPaths(map, pathE, trailheads)
        }
    }
}

fun findNext(map: Array<IntArray>, start: PathElement): List<PathElement> {
    val points = mutableListOf<PathElement>()
    nextPointUp(map, start)?.also { points.add(it) }
    nextPointDown(map, start)?.also { points.add(it) }
    nextPointLeft(map, start)?.also { points.add(it) }
    nextPointRight(map, start)?.also { points.add(it) }
    return points
}

fun nextPointLeft(map: Array<IntArray>, currentPath: PathElement): PathElement? {
    val current = currentPath.pos
    if (current.x > 0) {
        val left = map[current.y][current.x - 1]
        if (left == map[current.y][current.x] - 1) {
            return PathElement(Point(current.x - 1, current.y), currentPath, currentPath.hill)
        }
    }
    return null
}

fun nextPointUp(map: Array<IntArray>, currentPath: PathElement): PathElement? {
    val current = currentPath.pos
    if (current.y > 0) {
        val up = map[current.y -1][current.x]
        if (up == map[current.y][current.x] - 1) {
            return PathElement(Point(current.x, current.y - 1), currentPath, currentPath.hill)
        }
    }
    return null
}

fun nextPointRight(map: Array<IntArray>, currentPath: PathElement): PathElement? {
    val current = currentPath.pos
    if (current.x < map.size - 1) {
        val right = map[current.y][current.x + 1]
        if (right == map[current.y][current.x] - 1) {
            return PathElement(Point(current.x + 1, current.y), currentPath, currentPath.hill)
        }
    }
    return null
}

fun nextPointDown(map: Array<IntArray>, currentPath: PathElement): PathElement? {
    val current = currentPath.pos
    if (current.y < map[0].size - 1) {
        val down = map[current.y + 1][current.x]
        if (down == map[current.y][current.x] - 1) {
            return PathElement(Point(current.x, current.y + 1), currentPath, currentPath.hill)
        }
    }
    return null
}

fun findTrailheads(map: Array<IntArray>): MutableSet<Trailhead> {
    val trailheads = mutableSetOf<Trailhead>()
    for (y in map.indices) {
        for (x in map[y].indices) {
            val height = map[y][x]
            if (height == 0) {
                val th = Trailhead(Point(x, y), 0)
                trailheads.add(th)
            }
        }
    }
    return trailheads
}

fun solvePuzzleOne(fileName: String) : Int {
    val map = readMap(fileName)
    val trailheads = findTrailheads(map)
    val hills = findHills(map)
    hills.forEach {
        findPaths(map,PathElement(it, null, it), trailheads)
    }

    if(DEBUG) {
        trailheads.forEach {
            it.paths.forEach {
                val charMap = Array<CharArray>(map.size){ CharArray(map[0].size) {'.'} }
                var e: PathElement? = it
                do{
                    if (e != null) {
                        charMap[e.pos.y][e.pos.x] = Character.forDigit(map[e.pos.y][e.pos.x], 10)
                    }
                    e = e?.previous
                }while (e != null)
                printMap(charMap)
            }
        }
    }
    return trailheads.sumOf { it.reachablePeaks() }
}

fun solvePuzzleTwo(fileName: String) : Int {
    val map = readMap(fileName)
    val trailheads = findTrailheads(map)
    val hills = findHills(map)
    hills.forEach {
        findPaths(map,PathElement(it, null, it), trailheads)
    }

    if(DEBUG) {
        trailheads.forEach {
            it.paths.forEach {
                val charMap = Array<CharArray>(map.size){ CharArray(map[0].size) {'.'} }
                var e: PathElement? = it
                do{
                    if (e != null) {
                        charMap[e.pos.y][e.pos.x] = Character.forDigit(map[e.pos.y][e.pos.x], 10)
                    }
                    e = e?.previous
                }while (e != null)
                printMap(charMap)
            }
        }
    }
    return trailheads.sumOf { it.score }
}

fun printMap(mapCopy: Array<CharArray>) {
    print('\n')
    for(i in 0..(mapCopy[0].size -1) * 2 ) {
        print('_')
    }
    for (row in mapCopy) {
        print('\n')
        for (col in row) {
            print(col)
            print(' ')
        }
    }
}
