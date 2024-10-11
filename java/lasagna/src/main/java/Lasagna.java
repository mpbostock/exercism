public class Lasagna {

    private static final int EXPECTED_MINUTES_IN_OVEN = 40;

    public int expectedMinutesInOven() {
        return EXPECTED_MINUTES_IN_OVEN;
    }

    public int remainingMinutesInOven(int minutesInOven) {
        return expectedMinutesInOven() - minutesInOven;
    }

    public int preparationTimeInMinutes(int numberOfLayers) {
        return numberOfLayers * 2;
    }

    public int totalTimeInMinutes(int numberOfLayers, int minutesInOven) {
        return preparationTimeInMinutes(numberOfLayers) + minutesInOven;
    }
}
