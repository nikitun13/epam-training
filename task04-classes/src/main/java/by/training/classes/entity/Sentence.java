package by.training.classes.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class Sentence {

    private final List<Word> words;

    public Sentence(List<Word> words) {
        this.words = new ArrayList<>(words);
    }

    public void addWord(int index, Word word) {
        words.add(index, word);
    }

    public void addWord(Word word) {
        words.add(word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        final String WHITE_SPACE = " ";
        return words.stream()
                .map(Word::toString)
                .collect(joining(WHITE_SPACE));
    }
}
