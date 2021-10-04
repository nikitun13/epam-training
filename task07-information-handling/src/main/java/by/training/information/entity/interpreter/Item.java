package by.training.information.entity.interpreter;

/**
 * Describes interface of an entity
 * in Interpreter hierarchy for calculating
 * expressions.
 *
 * @author Nikita Romanov
 */
public interface Item {

    /**
     * Interprets operation or just returns
     * number value if concrete
     * entity isn't an operation.
     *
     * @return result of operation or number value.
     */
    int interpret();
}
