package by.training.classes.view.manager;

import java.util.ResourceBundle;

/**
 * The class {@code TextManager} is a utility class
 * that provides text from a {@code ResourceBundle}
 * that contains in {@link Language}.<br/>
 * Also changes the language of the application.
 * Delegates execution of methods to {@link ResourceBundle}.<br/>
 *
 * @author Nikita Romanov
 * @see ResourceBundle
 * @see Language
 */
public final class TextManager {

    private static ResourceBundle currentBundle
            = Language.EN.getBundle();

    private TextManager() {
    }

    /**
     * Gets a text for the given key from current {@code ResourceBundle}.
     *
     * @param key the key for the text from current {@code ResourceBundle}.
     * @return the text for the given key.
     * @throws NullPointerException               if {@code key} is {@code null}
     * @throws java.util.MissingResourceException if no text for
     *                                            the given key can be found
     * @throws ClassCastException                 if the object found for
     *                                            the given key is not a string
     */
    public static String getText(String key) {
        return currentBundle.getString(key);
    }

    /**
     * Determines whether the given {@code key}
     * is contained in current {@code ResourceBundle}.
     *
     * @param key the resource {@code key}.
     * @return {@code true} if the given {@code key} is
     * contained in current {@code ResourceBundle}; {@code false} otherwise.
     * @throws NullPointerException if {@code key} is {@code null}.
     */
    public static boolean isExist(String key) {
        return currentBundle.containsKey(key);
    }

    /**
     * Changes application language.
     * Replaces current {@code ResourceBundle} using {@link Language}.
     *
     * @param language new language
     */
    public static void setLanguage(Language language) {
        currentBundle = language.getBundle();
    }
}
