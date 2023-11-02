import scala.collection.mutable

object MatchingBrackets {
  sealed trait Bracket {
    val opening: Char
    val closing: Char
  }
  private case object Parenthesis extends Bracket {
    override val opening: Char = '('
    override val closing: Char = ')'
  }
  private case object Curly extends Bracket {
    override val opening: Char = '{'
    override val closing: Char = '}'
  }
  private case object Square extends Bracket {
    override val opening: Char = '['
    override val closing: Char = ']'
  }
  private case object Arrow extends Bracket {
    override val opening: Char = '<'
    override val closing: Char = '>'
  }
  private val isOpening: Char => Boolean = {
    case Parenthesis.opening | Curly.opening | Square.opening | Arrow.opening => true
    case _ => false
  }
  private val isClosing: Char => Boolean = {
    case Parenthesis.closing | Curly.closing | Square.closing | Arrow.closing => true
    case _ => false
  }
  private val fromOpening: Char => Bracket = {
    case Parenthesis.opening => Parenthesis
    case Curly.opening => Curly
    case Square.opening => Square
    case Arrow.opening => Arrow
  }
  private val fromClosing: Char => Bracket = {
    case Parenthesis.closing => Parenthesis
    case Curly.closing => Curly
    case Square.closing => Square
    case Arrow.closing => Arrow
  }
  def isPaired(brackets: String): Boolean = {
    val deque = mutable.ArrayDeque[Bracket]()
    for (c <- brackets) {
      val isOpen = isOpening(c)
      val isClose = isClosing(c)
      (isOpen, isClose) match {
        case (true, false) => deque += fromOpening(c)
        case (false, true) =>
          val bracket = fromClosing(c)
          val lastBracket = deque.lastOption
          if (lastBracket.isDefined && bracket == lastBracket.get) deque.removeLast() else deque += bracket
        case _ => // do nothing for other chars
      }
    }
    deque.isEmpty
  }
}
