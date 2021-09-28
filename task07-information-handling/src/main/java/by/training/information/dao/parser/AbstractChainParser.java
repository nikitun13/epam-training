package by.training.information.dao.parser;

/**
 * The class {@code AbstractChainParser} is an abstract class
 * that implements {@link StringToTextComponentsParser}.<br/>
 * This class is used for chain of responsibility design pattern.
 *
 * @author Nikita Romanov
 * @see StringToTextComponentsParser
 */
public abstract class AbstractChainParser
        implements StringToTextComponentsParser {

    /**
     * Final parser in a chain of parsers.
     */
    private static final StringToTextComponentsParser FINAL_PARSER;

    static {
        FINAL_PARSER = new StringToSymbolParser();
    }

    /**
     * Next parser in a chain of parsers.
     */
    protected StringToTextComponentsParser nextParser
            = FINAL_PARSER;

    /**
     * Sets next parser in a chain of parsers.
     *
     * @param nextParser next parser in the chain.
     * @return this parser for creating the chain.
     */
    public AbstractChainParser setNextParser(
            final AbstractChainParser nextParser) {
        this.nextParser = nextParser;
        return nextParser;
    }
}
