object HandshakeCalculator {
  fun calculateHandshake(number: Int): List<Signal> {
    val signals = mutableListOf<Signal>()

    return when {
      number >= 32 -> signals
      else -> number.toString(2).reversed().withIndex().fold(signals) { acc, curr ->
        when (curr.value) {
          '1' -> when (curr.index) {
            4 -> acc.asReversed()
            else -> {
              acc += Signal.values()[curr.index]
              acc
            }
          }

          else -> acc
        }
      }
    }
  }
}
