public class ExperimentalRemoteControlCar implements RemoteControlCar {
    private static final int DRIVING_DISTANCE = 20;
    private int distanceTravelled = 0;
    @Override
    public void drive() {
        distanceTravelled += DRIVING_DISTANCE;
    }

    @Override
    public int getDistanceTravelled() {
        return distanceTravelled;
    }
}
