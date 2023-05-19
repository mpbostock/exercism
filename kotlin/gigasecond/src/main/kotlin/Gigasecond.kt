import java.time.LocalDate
import java.time.LocalDateTime

class Gigasecond(start: LocalDateTime) {
    constructor(start: LocalDate) : this(start.atStartOfDay())

    private val ONE_GIGASECOND = 1000000000L

    val date: LocalDateTime = start.plusSeconds(ONE_GIGASECOND);
}
