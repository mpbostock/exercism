object SpaceAge {
  private val toEarthYears = (age: Double, factor: Double) => age / 31557600 / factor

  def onEarth(age: Double): Double = toEarthYears(age, 1)

  def onVenus(age: Double): Double = toEarthYears(age, 0.61519726)

  def onMercury(age: Double): Double = toEarthYears(age, 0.2408467)

  def onMars(age: Double): Double = toEarthYears(age, 1.8808158)

  def onJupiter(age: Double): Double = toEarthYears(age, 11.862615)

  def onSaturn(age: Double): Double = toEarthYears(age, 29.447498)

  def onUranus(age: Double): Double = toEarthYears(age, 84.016846)

  def onNeptune(age: Double): Double = toEarthYears(age, 164.79132)
}
