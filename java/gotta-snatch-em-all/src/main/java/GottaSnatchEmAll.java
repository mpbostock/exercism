import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return Set.copyOf(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        return !myCollection.containsAll(theirCollection) && !theirCollection.containsAll(myCollection);
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        int numSets = collections.size();
        if (numSets == 1) {
            return collections.get(0);
        } else if (numSets > 1) {
            Set<String> common = new HashSet<>(collections.get(0));
            IntStream.range(1, numSets).mapToObj(collections::get).forEach(common::retainAll);
            return common;
        }
        return Collections.emptySet();
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }
}
