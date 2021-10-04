package by.training.information.entity.interpreter;

/**
 * The class {@code Operation} is
 * an abstract class that implements {@link Item}
 * and represents node in Interpreter tree hierarchy.
 *
 * @author Nikita Romanov
 */
public abstract class Operation implements Item {

    protected Item left;
    protected Item right;

    protected Operation() {
    }

    protected Operation(final Item left, final Item right) {
        this.left = left;
        this.right = right;
    }

    public Item getLeft() {
        return left;
    }

    public void setLeft(final Item left) {
        this.left = left;
    }

    public Item getRight() {
        return right;
    }

    public void setRight(final Item right) {
        this.right = right;
    }
}
