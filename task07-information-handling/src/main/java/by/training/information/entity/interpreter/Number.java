package by.training.information.entity.interpreter;

/**
 * The class {@code Number} is a record class that
 * implements {@link Item} and represents leaf
 * in Interpreter tree hierarchy.
 *
 * @param value number's value.
 * @author Nikita Romanov
 */
public record Number(int value) implements Item {

    @Override
    public int interpret() {
        return value;
    }
}
