object Hamming {
  def distance(dna1: String, dna2: String): Option[Int] = {
    if (dna1.length == dna2.length) Some(dna1.zip(dna2).count(x => x._1 != x._2)) else None
  }
}
