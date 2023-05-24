class Robot {
  companion object {
    private val letters = 'A'..'Z'
    private val digits = '0'..'9'
    private val existingNames = mutableSetOf<String>()
    private val digit get() = digits.random()
    private val letter get() = letters.random()
  }

  var name: String = generateName()
    private set

  private fun generateName(): String {
    val name = "${letter}${letter}${digit}${digit}${digit}"
    return when {
      existingNames.add(name) -> name
      else -> generateName()
    }
  }

  fun reset() {
    name = generateName()
  }
}
