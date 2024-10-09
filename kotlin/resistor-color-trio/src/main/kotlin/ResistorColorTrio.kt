import Unit.*

object ResistorColorTrio {

    fun text(vararg input: Color): String {
        check(input.size == 3)
        val codes = input.map { it.ordinal }
        val numZeros = codes[2] + (if (codes[1] == 0) 1 else 0)
        val unit = nearestUnit(numZeros)
        val remainderZeros = numZeros - unit.numZeros()

        val value = "${codes[0]}${if (codes[1] == 0) "" else codes[1]}${"".padEnd(remainderZeros, '0')}"
        return "$value ${unit.toString().lowercase()}"
    }

    private fun Unit.numZeros() = when (this) {
        OHMS -> 0
        KILOOHMS -> 3
        MEGAOHMS -> 6
        GIGAOHMS -> 9
        TERAOHMS -> 12
        PETAOHMS -> 15
        EXAOHMS -> 18
    }

    private fun nearestUnit(numZeros: Int): Unit {
        return Unit.values().last { it.numZeros() <= numZeros }
    }
}
