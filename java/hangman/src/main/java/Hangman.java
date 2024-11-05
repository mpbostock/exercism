import io.reactivex.Observable;

import java.util.ArrayList;

class Hangman {
    private Throwable error;

    Observable<Output> play(Observable<String> words, Observable<String> letters) {
        ArrayList<Output> outputs = new ArrayList<>();
        words.subscribe(secret -> outputs.add(Output.initial(secret)));
        letters.subscribe(guess -> outputs.add(Output.forGuess(outputs.get(outputs.size() - 1), guess)), err -> error = err);
        return error != null ? Observable.error(error) : Observable.fromIterable(outputs);
    }

}