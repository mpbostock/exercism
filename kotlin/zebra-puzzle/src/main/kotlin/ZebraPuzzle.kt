import kotlin.math.abs

enum class Colour {
  Red, Green, Ivory, Yellow, Blue
}

enum class Nationality {
  English, Spaniard, Ukranian, Norwegian, Japanese
}

enum class Pet {
  Dog, Snails, Fox, Horse, Zebra
}

enum class Beverage {
  Coffee, Tea, Milk, OrangeJuice, Water
}

enum class Cigarette {
  OldGold, Kools, Chesterfields, LuckyStrike, Parliaments
}

data class House(
  val colour: Colour,
  val nationality: Nationality,
  val pet: Pet,
  val beverage: Beverage,
  val cigarette: Cigarette
)

private inline fun <reified T : Enum<T>> permutations() = enumValues<T>().toList().permutations()
private fun <T> List<T>.permutations(): Sequence<List<T>> =
  if (size <= 1) sequenceOf(this)
  else asSequence().flatMap { x -> (this - x).permutations().map { listOf(x) + it } }

class ZebraPuzzle {
  companion object {
    private val validColoursFilter: (List<Colour>) -> Boolean = {
      it.indexOf(Colour.Blue) == 1 && // as blue is next to norwegian and norwegian is 0
          it.indexOf(Colour.Green) == it.indexOf(Colour.Ivory) + 1
    }
    private val validNationalitiesFilter: (List<Nationality>, List<Colour>) -> Boolean = { nationalities, colours ->
      nationalities.indexOf(Nationality.Norwegian) == 0 &&
          nationalities.indexOf(Nationality.English) == colours.indexOf(Colour.Red)
    }
    private val validPetsFilter: (List<Pet>, List<Nationality>) -> Boolean = { pets, nationalities ->
      pets.indexOf(Pet.Dog) == nationalities.indexOf(Nationality.Spaniard)
    }
    private val validBeveragesFilter: (List<Beverage>, List<Colour>, List<Nationality>) -> Boolean = { beverages, colours, nationalities ->
      beverages.indexOf(Beverage.Milk) == 2 &&
          beverages.indexOf(Beverage.Tea) == nationalities.indexOf(Nationality.Ukranian) &&
          beverages.indexOf(Beverage.Coffee) == colours.indexOf(Colour.Green)
    }
    private val validCigarettesFilter: (List<Cigarette>, List<Colour>, List<Nationality>, List<Pet>, List<Beverage>) -> Boolean = { cigarettes, colours, nationalities, pets, beverages ->
      cigarettes.indexOf(Cigarette.OldGold) == pets.indexOf(Pet.Snails) &&
          cigarettes.indexOf(Cigarette.Kools) == colours.indexOf(Colour.Yellow) &&
          abs(cigarettes.indexOf(Cigarette.Chesterfields) - pets.indexOf(Pet.Fox)) == 1 &&
          abs(cigarettes.indexOf(Cigarette.Kools) - pets.indexOf(Pet.Horse)) == 1 &&
          cigarettes.indexOf(Cigarette.LuckyStrike) == beverages.indexOf(Beverage.OrangeJuice) &&
          cigarettes.indexOf(Cigarette.Parliaments) == nationalities.indexOf(Nationality.Japanese)
    }

    private val houses = solveHouses()
    private fun solveHouses(): List<House> {
      for (colours in colourPermutations()) {
        for (nationalities in nationalityPermutations(colours)) {
          for (pets in petPermutations(nationalities)) {
            for (beverages in beveragePermutations(colours, nationalities)) {
              for (cigarettes in cigarettePermutations(colours, nationalities, pets, beverages)) {
                return (0..4).map { House(colours[it], nationalities[it], pets[it], beverages[it], cigarettes[it]) }
              }
            }
          }
        }
      }
      throw Exception("No valid house combination found")
    }

    private fun colourPermutations() = permutations<Colour>().filter(validColoursFilter)

    private fun nationalityPermutations(colours: List<Colour>) =
      permutations<Nationality>().filter { validNationalitiesFilter(it, colours) }

    private fun petPermutations(nationalities: List<Nationality>) =
      permutations<Pet>().filter { validPetsFilter(it, nationalities) }

    private fun beveragePermutations(
      colours: List<Colour>,
      nationalities: List<Nationality>
    ) = permutations<Beverage>().filter { validBeveragesFilter(it, colours, nationalities) }

    private fun cigarettePermutations(
      colours: List<Colour>,
      nationalities: List<Nationality>,
      pets: List<Pet>,
      beverages: List<Beverage>
    ) = permutations<Cigarette>().filter { validCigarettesFilter(it, colours, nationalities, pets, beverages) }

  }

  fun drinksWater() = findResident{ it.beverage == Beverage.Water }
  fun ownsZebra() = findResident{ it.pet == Pet.Zebra }

  private fun findResident(predicate: (House) -> Boolean) = houses.single(predicate).nationality.name
}


