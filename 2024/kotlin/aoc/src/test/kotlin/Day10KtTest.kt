import org.example.PathElement
import org.example.findPaths
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10KtTest {

    @Test
    fun readMap() {
        val map = org.example.readMap("/day10/testinput_minimap.txt")
        assertArrayEquals(intArrayOf(0, 1, 2, 3), map[0])
        assertArrayEquals(intArrayOf(1, 2, 3, 4), map[1])
        assertArrayEquals(intArrayOf(8, 7, 6, 5), map[2])
        assertArrayEquals(intArrayOf(9, 8, 7, 6), map[3])
    }

    @Test
    fun findHills() {
        var map = org.example.readMap("/day10/testinput_minimap.txt")
        var hills = org.example.findHills(map)
        assertEquals(1, hills.size)
        assertEquals(0, hills[0].x)
        assertEquals(3, hills[0].y)

        map = org.example.readMap("/day10/testinput_twohills.txt")
        hills = org.example.findHills(map)
        assertEquals(2, hills.size)
        assertEquals(0, hills[0].x)
        assertEquals(6, hills[0].y)
        assertEquals(6, hills[1].x)
        assertEquals(6, hills[1].y)
    }

    @Test
    fun findTrailheads() {
        val map = org.example.readMap("/day10/testinput_minimap.txt")
        val trailheads = org.example.findTrailheads(map)
        assertEquals(1, trailheads.size)
        assertEquals(0, trailheads.first().pos.x)
        assertEquals(0, trailheads.first().pos.y)
        assertEquals(0, trailheads.first().score)
    }

    @Test
    fun findPathsAndScroe() {
        val map = org.example.readMap("/day10/testinput_twohills.txt")
        val trailheads = org.example.findTrailheads(map)
        val hills = org.example.findHills(map)
        hills.forEach {
            findPaths(map, PathElement(it, null, it), trailheads)
        }
        assertEquals(2, trailheads.first().score)
    }

    @Test
    fun testSolvePuzzle1() {
        val result = org.example.solvePuzzleOne("/day10/testinput_larger.txt")
        assertEquals(36 , result)
    }

    @Test
    fun testSolvePuzzle2() {
        val result = org.example.solvePuzzleTwo("/day10/testinput_larger.txt")
        assertEquals(81 , result)
    }
}