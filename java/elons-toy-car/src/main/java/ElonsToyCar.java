public class ElonsToyCar {
    private int distanceDriven = 0;
    private int batteryPercentage = 100;
    public static ElonsToyCar buy() {
        return new ElonsToyCar();
    }

    public String distanceDisplay() {
        return String.format("Driven %d meters", distanceDriven);
    }

    public String batteryDisplay() {
        return batteryPercentage > 0 ? String.format("Battery at %d%%", batteryPercentage) : "Battery empty";
    }

    public void drive() {
        if (batteryPercentage > 0) {
            distanceDriven += 20;
            batteryPercentage -= 1;
        }
    }
}
