package by.training.information.entity.interpreter;

import java.util.function.IntBinaryOperator;

/**
 * The class {@code BinaryOperation} is a class that
 * extends {@link Operation} and represents
 * binary operation in Interpreter hierarchy.
 *
 * @author Nikita Romanov
 */
public class BinaryOperation extends Operation {

    private final IntBinaryOperator operator;

    public BinaryOperation(final IntBinaryOperator operator) {
        this.operator = operator;
    }

    public BinaryOperation(final Item left, final Item right,
                           final IntBinaryOperator operator) {
        super(left, right);
        this.operator = operator;
    }

    @Override
    public int interpret() {
        return operator.applyAsInt(left.interpret(), right.interpret());
    }
}
