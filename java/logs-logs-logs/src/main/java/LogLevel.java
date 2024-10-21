public enum LogLevel {
    TRACE(1),
    DEBUG(2),
    INFO(4),
    WARNING(5),
    ERROR(6),
    FATAL(42),
    UNKNOWN(0);

    private final int encoded;

    LogLevel(int encoded) {
        this.encoded = encoded;
    }

    public int getEncoded() {
        return encoded;
    }

    public static LogLevel fromLevel(String level) {
        switch (level) {
            case "TRC":
                return TRACE;
            case "DBG":
                return DEBUG;
            case "INF":
                return INFO;
            case "WRN":
                return WARNING;
            case "ERR":
                return ERROR;
            case "FTL":
                return FATAL;
            default:
                return UNKNOWN;
        }
    }
}
