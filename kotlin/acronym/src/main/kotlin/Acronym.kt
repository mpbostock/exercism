object Acronym {
    fun generate(phrase: String) : String {
        val words = phrase.split(" ", "-").filterNot { it.isEmpty() }.map { it.replace("_", "") }
        return words.joinToString("") { it.first().uppercase() }
    }
}
