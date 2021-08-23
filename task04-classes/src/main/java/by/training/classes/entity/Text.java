package by.training.classes.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * The class {@code Text} represents entity
 * of a text.<br>
 * Contains a list of {@link Sentence} that form a {@code Text}.
 *
 * @author Nikita Romanov
 * @see Sentence
 */
public class Text {

    private Sentence header;
    private final List<Sentence> sentences;

    public Text(Sentence header, List<Sentence> sentences) {
        this.header = header;
        this.sentences = new LinkedList<>(sentences);
    }

    public Sentence removeSentence(int index) {
        return sentences.remove(index);
    }

    public void addSentence(int index, Sentence sentence) {
        sentences.add(index, sentence);
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public Sentence getHeader() {
        return header;
    }

    public void setHeader(Sentence header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(header, text.header)
                && Objects.equals(sentences, text.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, sentences);
    }

    @Override
    public String toString() {
        return header
                + System.lineSeparator()
                + System.lineSeparator()
                + getBody();
    }

    private String getBody() {
        final String dotWithWhiteSpace = ". ";
        return sentences.stream()
                .map(Sentence::toString)
                .collect(joining(dotWithWhiteSpace));
    }
}
