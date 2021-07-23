package by.epam.linear.task;

/*
 * 40. The value 'x' is given.
 * Get the values -2x + 3x^2 - 4x^3 and 1 + 2x + 3x^2 + 4x^3.
 * Take care of saving operations.
 */
public final class Task40 {

    private Task40() {
    }

    public static long calculateFirstExpression(long x) {
        return (long) (x * (-4 * Math.pow(x, 2) + 3 * x - 2)); //x * (-4x^2 + 3x - 2)
    }

    public static long calculateSecondExpression(long x) {
        return (long) (4 * Math.pow(x, 3) + 3 * Math.pow(x, 2) + 2 * x + 1); // 4x^3 + 3x^2 + 2x + 1
    }
}
