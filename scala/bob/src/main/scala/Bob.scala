object Bob {
  def response(statement: String): String = {
    val trimmed = statement.trim
    if (trimmed.isEmpty) return "Fine. Be that way!"
    val isShouting = trimmed.exists(_.isLetter) && trimmed.toUpperCase == trimmed
    val isQuestion = trimmed.endsWith("?")
    (isQuestion, isShouting) match {
      case (true, true) => "Calm down, I know what I'm doing!"
      case (_, true) => "Whoa, chill out!"
      case (true, _) => "Sure."
      case _ => "Whatever."
    }
  }
}
