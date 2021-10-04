package by.training.information.entity;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Enumeration of provided operators.
 *
 * @author Nikita Romanov
 */
public enum Operator {

    BITWISE_UNARY_NOT(13, "~"),
    SIGNED_RIGHT_SHIFT(10, ">>"),
    SIGNED_LEFT_SHIFT(10, "<<"),
    UNSIGNED_RIGHT_SHIFT(10, ">>>"),
    BITWISE_AND(7, "&"),
    BITWISE_EXCLUSIVE_OR(6, "^"),
    BITWISE_OR(5, "|"),
    LEFT_BRACKET(1, "("),
    RIGHT_BRACKET(1, ")");

    private static final Map<String, Operator> PROVIDER;

    static {
        PROVIDER = Arrays.stream(values())
                .collect(toMap(Operator::getSymbol, identity()));
    }

    private final int precedence;
    private final String symbol;

    Operator(final int precedence, final String symbol) {
        this.precedence = precedence;
        this.symbol = symbol;
    }

    public static Optional<Operator> getOperatorBySymbol(final String symbol) {
        return Optional.ofNullable(PROVIDER.get(symbol));
    }

    public int getPrecedence() {
        return precedence;
    }

    public String getSymbol() {
        return symbol;
    }
}
