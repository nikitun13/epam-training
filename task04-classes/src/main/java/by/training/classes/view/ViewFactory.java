package by.training.classes.view;

public final class ViewFactory {

    private static final ViewFactory INSTANCE = new ViewFactory();

    private final View view = new ConsoleView();

    private ViewFactory() {
    }

    public static ViewFactory getInstance() {
        return INSTANCE;
    }

    public View getView() {
        return view;
    }
}
