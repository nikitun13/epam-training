package by.training.information.entity;

import java.util.List;

/**
 * Describes interface of a text component entity.
 *
 * @author Nikita Romanov
 */
public interface TextComponent {

    /**
     * Collects values from children and combines them.
     * Or returns its value if it is a leaf.
     *
     * @return combined value of children.
     */
    String collect();

    /**
     * Returns children components in the composite hierarchy.
     *
     * @return list of children components.
     */
    List<TextComponent> getChildrenComponents();

    /**
     * Returns type of the {@code TextComponent}.
     *
     * @return type of the {@code TextComponent}.
     */
    Type getType();

    /**
     * Makes copy of the {@code TextComponent}.
     *
     * @return copy of the {@code TextComponent}.
     */
    TextComponent copyComponent();

    enum Type {

        /**
         * Text type of the TextComponent.
         */
        TEXT("\n"),
        /**
         * Paragraph type of the TextComponent.
         */
        PARAGRAPH(" ", "    "),
        /**
         * Sentence type of the TextComponent.
         */
        SENTENCE(" "),
        /**
         * Lexeme type of the TextComponent.
         */
        LEXEME,
        /**
         * Word type of the TextComponent.
         */
        WORD,
        /**
         * Expression type of the TextComponent.
         */
        EXPRESSION,
        /**
         * Symbol type of the TextComponent.
         */
        SYMBOL;

        /**
         * Default delimiter or prefix.
         */
        private static final String DEFAULT_SEPARATOR = "";

        private final String childDelimiter;
        private final String prefix;

        Type() {
            this(DEFAULT_SEPARATOR, DEFAULT_SEPARATOR);
        }

        Type(final String childDelimiter) {
            this(childDelimiter, DEFAULT_SEPARATOR);
        }

        Type(final String childDelimiter, final String prefix) {
            this.childDelimiter = childDelimiter;
            this.prefix = prefix;
        }

        public String getChildDelimiter() {
            return childDelimiter;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
