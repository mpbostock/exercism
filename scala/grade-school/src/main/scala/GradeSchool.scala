import scala.math.Ordering.Implicits._

class School {
  type DB = Map[Int, Seq[String]]
  private var _db: DB = Map.empty
  private val sortedStudentsForGrade = (g: Int) => g -> grade(g).sorted

  def add(name: String, g: Int) = _db = _db.updated(g, grade(g) :+ name)

  def db: DB = _db

  def grade(g: Int): Seq[String] = _db.getOrElse(g, Seq.empty)

  def sorted: DB = _db.keys.toSeq.sorted.map(sortedStudentsForGrade).toMap
}

