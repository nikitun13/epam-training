package by.training.classes.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class Text {

    private Sentence title;
    private final List<Sentence> sentences;

    public Text(Sentence title, List<Sentence> sentences) {
        this.title = title;
        this.sentences = new LinkedList<>(sentences);
    }

    public void removeSentence(int index) {
        sentences.remove(index);
    }

    public void addSentence(int index, Sentence sentence) {
        sentences.add(index, sentence);
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public Sentence getTitle() {
        return title;
    }

    public void setTitle(Sentence title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return Objects.equals(title, text.title) && Objects.equals(sentences, text.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, sentences);
    }

    @Override
    public String toString() {
        return title
                + System.lineSeparator()
                + System.lineSeparator()
                + getBody();
    }

    private String getBody() {
        final String WHITE_SPACE = " ";
        return sentences.stream()
                .map(Sentence::toString)
                .collect(joining(WHITE_SPACE));
    }
}
