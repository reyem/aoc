import org.example.day11.solvePuzzleTwo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun readBlocks() {
        val blocks = org.example.day11.readBlocks("/day11/testinput.txt")
        assertEquals(2, blocks.size)
        assertEquals(125, blocks[0])
        assertEquals(17, blocks[1])
    }

    @Test
    fun solvePuzzle2() {
        val result = solvePuzzleTwo("/day11/testinput.txt")
        assertEquals(65601038650482, result)
    }

}