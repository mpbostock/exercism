class Matrix(private val matrixAsString: String) {
    private val matrix: Array<IntArray> = initialiseMatrix()
    private val height = matrix.size

    private fun initialiseMatrix() = matrixAsString.split('\n').map { row ->
                row.split(' ').map { it.toInt() }.toIntArray()
            }.toTypedArray()

    fun column(colNr: Int): List<Int> {
        return (0 until height).map { matrix[it][colNr - 1] }.toList()
    }

    fun row(rowNr: Int): List<Int> {
        return matrix[rowNr - 1].toList()
    }
}
