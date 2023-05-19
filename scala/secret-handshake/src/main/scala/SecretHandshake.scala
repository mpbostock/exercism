object SecretHandshake {
  private val byteIndexCommandMap = Map(0 -> "wink", 1 -> "double blink", 2 -> "close your eyes", 3 -> "jump")
  def commands(decimal: Int): List[String] = {
    val binary = decimal.toBinaryString.reverse
    binary.zipWithIndex.foldLeft(List[String]()) ((acc, curr) => {
      (curr._1, curr._2 == 4) match {
        case ('1', true) => acc.reverse
        case ('1', _) => acc :+ byteIndexCommandMap(curr._2)
        case _ => acc
      }
    })
  }
}