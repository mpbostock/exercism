import kotlin.math.pow

object ArmstrongNumber {

    fun check(input: Int): Boolean {
        val digits = input.toString().map { it.digitToInt() }
        val sumOfPower = digits.sumOf { it.toDouble().pow(digits.size).toInt() }
        return input == sumOfPower
    }

}
