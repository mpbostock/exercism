import java.util.*;

class Output {

    public final String secret;
    public final String discovered;
    public final Set<String> guess;
    public final Set<String> misses;
    public final List<Part> parts;
    public final Status status;

    Output(
        final String secret,
        final String discovered,
        final Set<String> guess,
        final Set<String> misses,
        final List<Part> parts,
        final Status status) {
        this.secret = secret;
        this.discovered = discovered;
        this.guess = Set.copyOf(guess);
        this.misses = Set.copyOf(misses);
        this.parts = List.copyOf(parts);
        this.status = status;
    }

    static Output initial(final String secret) {
        return new Output(secret,
                "_".repeat(secret.length()),
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptyList(),
                Status.PLAYING);
    }

    static Output forGuess(final Output previous, final String guess) {
        String secret = previous.secret;
        boolean correctGuess = secret.contains(guess);
        char letter = guess.charAt(0);
        Set<String> guesses = new HashSet<>(previous.guess);
        Set<String> misses = new HashSet<>(previous.misses);
        List<Part> parts = new ArrayList<>(previous.parts);
        String discovered = previous.discovered;
        if(guesses.contains(guess) || misses.contains(guess)) {
            throw new IllegalArgumentException(String.format("Letter %s was already played", guess));
        }
        if (correctGuess) {
            guesses.add(guess);
            discovered = fillDiscovered(discovered, secret, letter);
        } else {
            misses.add(guess);
            parts.add(Part.values()[parts.size()]);
        }
        Status status = previous.status;
        if (Objects.equals(discovered, secret)) {
            status = Status.WIN;
        } else if (parts.size() == Part.values().length) {
            status = Status.LOSS;
        }
        return new Output(
                secret,
                discovered,
                guesses,
                misses,
                parts,
                status
        );
    }

    private static String fillDiscovered(String discovered, String secret, char letter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < discovered.length(); i++) {
            if (secret.charAt(i) == letter) {
                builder.append(letter);
            } else {
                builder.append(discovered.charAt(i));
            }
        }
        return builder.toString();
    }

}
