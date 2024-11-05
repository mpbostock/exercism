import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

class BookStore {
    private static final double BOOK_COST = 8.0;
    private static final double[] GROUP_PRICES = {
            0.0,
            BOOK_COST,
            2 * BOOK_COST * 0.95,
            3 * BOOK_COST * 0.9,
            4 * BOOK_COST * 0.8,
            5 * BOOK_COST * 0.75,
    };

    private static int[] getBookGroups(List<Integer> books) {
        Map<Integer, Long> bookCounts = books.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
        int[] bookGroups = new int[6];
        while (!bookCounts.isEmpty()) {
            bookGroups[bookCounts.size()]++;
            bookCounts.entrySet().forEach(e -> e.setValue(e.getValue() - 1));
            bookCounts.entrySet().removeIf(e -> e.getValue() == 0);
        }
        // swap groups of 5 and 3 for two groups of 4
        while (bookGroups[3] > 0 && bookGroups[5] > 0) {
            bookGroups[3]--;
            bookGroups[5]--;
            bookGroups[4] += 2;
        }
        return bookGroups;
    }

    private static double calculateTotal(int[] bookGroups) {
        return IntStream.range(1, bookGroups.length).mapToDouble(i -> bookGroups[i] * GROUP_PRICES[i]).sum();
    }

    double calculateBasketCost(List<Integer> books) {
        int[] bookGroups = getBookGroups(books);
        return calculateTotal(bookGroups);
    }
}