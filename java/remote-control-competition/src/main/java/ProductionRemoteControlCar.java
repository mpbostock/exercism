public class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar> {
    private static final int DRIVING_DISTANCE = 10;
    private int distanceTravelled = 0;
    private int numVictories = 0;

    @Override
    public void drive() {
        distanceTravelled += DRIVING_DISTANCE;
    }

    @Override
    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public int getNumberOfVictories() {
        return numVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        numVictories = numberOfVictories;
    }

    @Override
    public int compareTo(ProductionRemoteControlCar o) {
        return Integer.compare(o.getNumberOfVictories(), getNumberOfVictories());
    }
}
