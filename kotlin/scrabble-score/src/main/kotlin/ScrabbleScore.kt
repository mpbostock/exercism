object ScrabbleScore {

    private fun scoreLetter(c: Char): Int {
        return when (c.uppercaseChar()) {
            in "DG" -> 2
            in "BCMP" -> 3
            in "FHVWY" -> 4
            'K' -> 5
            in "JX" -> 8
            in "QZ" -> 10
            else -> 1
        }
    }

    fun scoreWord(word: String): Int {
        return word.sumOf { scoreLetter(it) }
    }
}
