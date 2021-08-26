package by.training.classes.view.manager;

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

    EN(ResourceBundle.getBundle("lang.text", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("lang.text", new Locale("ru", "RU")));

    private final ResourceBundle bundle;

    Language(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public static boolean isContains(String lang) {
        for (Language value : Language.values()) {
            if (value.name().equals(lang)) {
                return true;
            }
        }
        return false;
    }
}
