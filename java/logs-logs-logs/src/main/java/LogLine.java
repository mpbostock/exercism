public class LogLine {
    private final LogLevel level;
    private final String message;

    public LogLine(String logLine) {
        String[] split = logLine.split(":");
        level = LogLevel.fromLevel(split[0].substring(1, 4));
        message = split[1].trim();
    }

    public LogLevel getLogLevel() {
        return level;
    }

    public String getOutputForShortLog() {
        return String.format("%d:%s", getLogLevel().getEncoded(), message);
    }
}
