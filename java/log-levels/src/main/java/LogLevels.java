public class LogLevels {
    
    public static String message(String logLine) {
        return splitLogLine(logLine)[1].trim();
    }

    public static String logLevel(String logLine) {
        return splitLogLine(logLine)[0].replace("[", "").replace("]", "").toLowerCase();
    }

    public static String reformat(String logLine) {
        return String.format("%s (%s)", message(logLine), logLevel(logLine));
    }

    private static String[] splitLogLine(String logLine) {
        return logLine.split(": ");
    }
}
