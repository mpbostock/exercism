class Triangle private constructor(uniqueSidesCount: Int) {
  companion object {
    operator fun <T : Number> invoke(a : T, b: T, c: T): Triangle {
      val sides = listOf(a, b, c).map { it.toDouble() }.sorted()
      require(sides[0] + sides[1] > sides[2])
      return Triangle(setOf(a, b, c).size)
    }
  }

  val isEquilateral: Boolean = uniqueSidesCount == 1
  val isIsosceles: Boolean = uniqueSidesCount <= 2
  val isScalene: Boolean = uniqueSidesCount == 3
}
