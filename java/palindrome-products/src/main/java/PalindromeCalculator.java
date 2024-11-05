import java.util.*;

class PalindromeCalculator {

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }
        SortedMap<Long, List<List<Integer>>> palindromes = new TreeMap<>();
        for (int i = min; i <= max; i++) {
            for (int j = i; j <= max; j++) {
                long product = i * (long) j;
                if (isPalindrome(product)) {
                    palindromes.computeIfAbsent(product, k -> new ArrayList<>()).add(Arrays.asList(i, j));
                }
            }
        }
        return palindromes;
    }

    private boolean isPalindrome(long product) {
        String asString = Long.toString(product);
        return asString.contentEquals(new StringBuilder(asString).reverse());
    }

}