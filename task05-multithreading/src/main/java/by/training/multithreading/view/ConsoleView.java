package by.training.multithreading.view;

import java.util.Scanner;

/**
 * The class {@code ConsoleView} is a class that implements {@link View}.
 * Provides user interaction through the console.
 *
 * @author Nikita Romanov
 */
public class ConsoleView implements View {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void print(String message) {
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
