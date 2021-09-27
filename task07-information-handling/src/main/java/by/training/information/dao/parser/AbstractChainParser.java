package by.training.information.dao.parser;

/**
 * The class {@code AbstractChainParser} is an abstract class
 * that implements {@link TextComponentParser}.<br/>
 * This class is used for chain of responsibility design pattern.
 *
 * @author Nikita Romanov
 * @see TextComponentParser
 */
public abstract class AbstractChainParser implements TextComponentParser {

    /**
     * Final parser in a chain of parsers.
     */
    private static final TextComponentParser FINAL_PARSER;

    static {
        FINAL_PARSER = new SymbolParser();
    }

    /**
     * Next parser in a chain of parsers.
     */
    protected TextComponentParser nextParser = FINAL_PARSER;

    public AbstractChainParser setNextParser(
            final AbstractChainParser nextParser) {
        this.nextParser = nextParser;
        return nextParser;
    }
}
