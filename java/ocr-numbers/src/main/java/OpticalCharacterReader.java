import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static java.lang.Math.min;

class OpticalCharacterReader {
    private static final int ROW_HEIGHT = 4;
    private static final int COLUMN_WIDTH = 3;

    private final static Map<List<String>, String> characterMap = Map.of(
            List.of(" _ ",
                    "| |",
                    "|_|",
                    "   "), "0",
            List.of("   ",
                    "  |",
                    "  |",
                    "   "), "1",
            List.of(" _ ",
                    " _|",
                    "|_ ",
                    "   "), "2",
            List.of(" _ ",
                    " _|",
                    " _|",
                    "   "), "3",
            List.of("   ",
                    "|_|",
                    "  |",
                    "   "), "4",
            List.of(" _ ",
                    "|_ ",
                    " _|",
                    "   "), "5",
            List.of(" _ ",
                    "|_ ",
                    "|_|",
                    "   "), "6",
            List.of(" _ ",
                    "  |",
                    "  |",
                    "   "), "7",
            List.of(" _ ",
                    "|_|",
                    "|_|",
                    "   "), "8",
            List.of(" _ ",
                    "|_|",
                    " _|",
                    "   "), "9"
    );

    private static String parseLetter(List<String> input) {
        return characterMap.getOrDefault(input, "?");
    }

    private static String parseGrid(List<List<List<String>>> grid) {
        StringJoiner rowJoiner = new StringJoiner(",");
        for (List<List<String>> letters : grid) {
            StringJoiner letterJoiner = new StringJoiner("");
            for (List<String> letter : letters) {
                letterJoiner.add(parseLetter(letter));
            }
            rowJoiner.add(letterJoiner.toString());
        }
        return rowJoiner.toString();
    }

    private static List<List<List<String>>> buildGrid(List<String> input, int numRows, int numCols) {
        List<List<List<String>>> rows = new ArrayList<>();
        for (int row = 0; row < numRows; row = row + ROW_HEIGHT) {
            List<List<String>> letters = new ArrayList<>();
            for (int col = 0; col < numCols; col = col + COLUMN_WIDTH) {
                List<String> letter = new ArrayList<>();
                for (int i = row; i < row + ROW_HEIGHT; i++) {
                    String line = input.get(i);
                    letter.add(line.substring(col, min(col + COLUMN_WIDTH, numCols)));
                }
                letters.add(letter);
            }
            rows.add(letters);
        }
        return rows;
    }

    String parse(List<String> input) {
        int rows = input.size();
        int columns = rows > 0 ? input.get(0).length() : 0;
        if (rows % ROW_HEIGHT != 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }
        if (columns % COLUMN_WIDTH != 0) {
            throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        }
        return parseGrid(buildGrid(input, rows, columns));
    }

}