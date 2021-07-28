package by.training.linear.view;

public enum MenuItem {

    EXIT(0, "Exit"),
    AVERAGE_FINDER(1, "Find average of two numbers"),
    POWER_CALCULATOR(2, "Print first four powers of π"),
    CIRCLE_CALCULATOR(3, "Find the area of a circle bounded by a circumference"),
    RESISTANCE_CALCULATOR(4, "Find resistance of parallel resistors"),
    EXPRESSION_CALCULATOR(5, "Calculate two expressions");

    private final int num;
    private final String description;

    MenuItem(int num, String description) {
        this.num = num;
        this.description = description;
    }

    public int getNum() {
        return num;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", num, description);
    }
}
