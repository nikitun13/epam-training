package by.training.xml.view;

/**
 * The class {@code ViewFactory} is utility class
 * that provides the implementation of view.
 *
 * @author Nikita Romanov
 */
public final class ViewFactory {

    /**
     * Singleton instance.
     */
    private static ViewFactory instance;

    private final View view;

    private ViewFactory() {
        view = new ConsoleView();
    }

    public View getView() {
        return view;
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static ViewFactory getInstance() {
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }
}
