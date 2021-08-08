package by.training.arrays.view.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class {@code Language} is an enumeration that contains available application languages.
 * Also contains {@link ResourceBundle} that contains menu translations.
 * To get text from {@code ResourceBundle} or change the application language use {@link TextManager}.
 *
 * @author Nikita Romanov
 * @see TextManager
 * @see ResourceBundle
 */
public enum Language {

    EN(ResourceBundle.getBundle("lang.text", Locale.getDefault())),
    RU(ResourceBundle.getBundle("lang.text", new Locale("ru", "RU")));

    private final ResourceBundle bundle;

    Language(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
