package by.training.shapes.view.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class {@code Language} is an enumeration
 * that contains available application languages.<br/>
 * Also contains {@link ResourceBundle} with menu translations.<br/>
 * To get text from {@code ResourceBundle}
 * or change the application language use {@link TextManager}.
 *
 * @author Nikita Romanov
 * @see TextManager
 * @see ResourceBundle
 */
public enum Language {

    /**
     * English translations bundle.
     */
    EN(ResourceBundle.getBundle("lang.text", new Locale("en", "US"))),
    /**
     * Russian translations bundle.
     */
    RU(ResourceBundle.getBundle("lang.text", new Locale("ru", "RU")));

    /**
     * Bundle with translations.
     */
    private final ResourceBundle bundle;

    Language(final ResourceBundle newBundle) {
        bundle = newBundle;
    }

    /**
     * Getter.
     *
     * @return {@link ResourceBundle} with translations.
     */
    public ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * Checks if enumeration contains given language.
     *
     * @param lang given language.
     * @return {@code true} if enumeration contains given language,
     * {@code false} otherwise.
     */
    public static boolean isContains(final String lang) {
        for (Language value : Language.values()) {
            if (value.name().equals(lang)) {
                return true;
            }
        }
        return false;
    }
}
