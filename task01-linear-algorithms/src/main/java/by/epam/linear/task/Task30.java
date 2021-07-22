package by.epam.linear.task;

/*
 * Three resistors R1, R2, and R3 are connected in parallel.
 * Find the connection resistance.
 */
public final class Task30 {

    private Task30() {
    }

    public static double findResistanceOfParallelResistors(double r1, double r2, double r3) {
        if (r1 < 0 || r2 < 0 || r3 < 0) {
            return 0d; //returns 0 if one of resistors has negative resistance
        }
        return (r1 * r2 * r3) / (r1 * r2 + r2 * r3 + r3 * r1);
    }

}
