object Etl {
  def transform(scoreMap: Map[Int, Seq[String]]): Map[String, Int] =
    scoreMap.flatMap(s => s._2.map(_.toLowerCase -> s._1))
}
