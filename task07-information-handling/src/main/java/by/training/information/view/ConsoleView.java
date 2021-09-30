package by.training.information.view;

import java.util.Scanner;

/**
 * The class {@code ConsoleView} is a class that implements {@link View}.
 * Provides user interaction through the console.
 *
 * @author Nikita Romanov
 */
public class ConsoleView implements View {

    /**
     * {@link Scanner} for reading from the console.
     */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void println(final String message) {
        System.out.println(message);
    }

    @Override
    public void print(final String message) {
        System.out.print(message);
    }

    @Override
    public void printNewLine() {
        System.out.println();
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
