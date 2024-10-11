public class CarsAssemble {

    public static final int CARS_PER_HOUR = 221;
    public static final int MINUTES_PER_HOUR = 60;

    public double productionRatePerHour(int speed) {
        double successRate = 1.0;
        if (speed == 10) {
            successRate = 0.77;
        } else if (speed == 9) {
            successRate = 0.80;
        } else if (speed >= 5 && speed <= 8) {
            successRate = 0.9;
        }
        return speed * CARS_PER_HOUR * successRate;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / MINUTES_PER_HOUR);
    }
}
