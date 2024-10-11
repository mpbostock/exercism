import java.util.Map;

class SqueakyClean {
    private static final Map<Integer, Character> leetMap = Map.of(
            0, 'o',
            1, 'l',
            3, 'e',
            4, 'a',
            7, 't'
    );

    static String clean(String identifier) {
        StringBuilder builder = new StringBuilder();
        boolean capitaliseLetter = false;
        for (int i = 0; i < identifier.length(); i++) {
            char c = identifier.charAt(i);
            if (Character.isSpaceChar(c)) {
                builder.append('_');
            } else if (Character.isDigit(c)) {
                builder.append(leetMap.get(Character.digit(c, 10)));
            } else if (c == '-') {
                capitaliseLetter = true;
            } else {
                if (Character.isLetter(c)) {
                    builder.append(capitaliseLetter ? Character.toUpperCase(c) : c);
                    capitaliseLetter = false;
                }
            }
        }
        return builder.toString();
    }
}
