package by.epam.linear.task;

/*
 * 20. The circumference is known
 * Find the area of a circle bounded by this circumference.
 */
public final class Task20 {

    private Task20() {
    }

    public static double findCircleArea(long circumference) {
        if (circumference < 0) {
            return 0d; // if circumference < 0 than returns 0
        }
        return Math.pow(circumference, 2) / (4 * Math.PI);
    }
}
