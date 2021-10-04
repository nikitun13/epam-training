package by.training.information.entity.interpreter;

import java.util.function.IntUnaryOperator;

/**
 * The class {@code UnaryOperation} is a class that
 * extends {@link Operation} and represents
 * unary operation in Interpreter hierarchy.
 *
 * @author Nikita Romanov
 */
public class UnaryOperation extends Operation {

    private static final Item PRESENT = new Number(0);
    private final IntUnaryOperator operator;

    public UnaryOperation(final IntUnaryOperator operator) {
        this(null, operator);
    }

    public UnaryOperation(final Item next,
                          final IntUnaryOperator operator) {
        super(next, PRESENT);
        this.operator = operator;
    }

    @Override
    public int interpret() {
        return operator.applyAsInt(left.interpret());
    }
}
