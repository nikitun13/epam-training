package by.training.information.service.validator;

/**
 * The class {@code ExpressionValidator}
 * is a class that implements {@link Validator}.<br/>
 * Validates expressions in String representation entities.
 *
 * @author Nikita Romanov
 * @see Validator
 */
public class ExpressionValidator implements Validator<String> {

    /**
     * Expression regex.
     */
    private static final String EXPRESSION_REGEX
            = "((\\(*~?\\d+([<]{2}|[>]{2,3}|[\\^&|]))+"
            + "(~?\\d+\\)*([<]{2}|[>]{2,3}|[\\^&|])?)+)+";
    /**
     * Singleton.
     */
    private static ExpressionValidator instance;

    /**
     * Getter for singleton.
     *
     * @return singleton instance of {@link ExpressionValidator} class.
     */
    public static ExpressionValidator getInstance() {
        if (instance == null) {
            instance = new ExpressionValidator();
        }
        return instance;
    }

    @Override
    public boolean isValid(final String o) {
        return o != null
                && !o.isBlank()
                && o.matches(EXPRESSION_REGEX);
    }
}
