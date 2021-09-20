package by.training.shapes.view;

/**
 * Describes the interface of a view for user interaction.
 *
 * @author Nikita Romanov
 */
public interface View {

    /**
     * Prints {@code message} for user.
     * Adds a line break to the end of the {@code message}.
     *
     * @param message message for user.
     */
    void println(String message);

    /**
     * Prints {@code message} for user.
     * Doesn't add a line break to the end of the {@code message}.
     *
     * @param message message for user.
     */
    void print(String message);

    /**
     * Adds a line break.
     */
    void printNewLine();

    /**
     * Reads a line from user input.
     *
     * @return user input.
     */
    String read();
}
