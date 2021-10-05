package by.training.information.service;

import by.training.information.service.impl.*;

/**
 * The class {@code ServiceFactory} is utility class
 * that provides the implementation of services.
 *
 * @author Nikita Romanov
 */
public final class ServiceFactory {

    /**
     * Singleton instance.
     */
    private static ServiceFactory instance;

    private final TextService textService;
    private final TextSortingService textSortingService;
    private final ReversePolishNotationCreatorService polishNotationCreatorService;
    private final PolishNotationCalculatorService polishNotationCalculatorService;
    private final ProxyExpressionsInTextService proxyExpressionsInTextService;

    private ServiceFactory() {
        textService = new TextServiceImpl();
        textSortingService = new TextSortingServiceImpl();
        polishNotationCreatorService = new ReversePolishNotationCreatorServiceImpl();
        polishNotationCalculatorService = new PolishNotationCalculatorServiceImpl();
        proxyExpressionsInTextService = new ProxyExpressionsInTextServiceImpl();
    }

    /**
     * Getter for singleton instance.
     *
     * @return instance of this class.
     */
    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public TextService getTextService() {
        return textService;
    }

    public TextSortingService getTextSortingService() {
        return textSortingService;
    }

    public ReversePolishNotationCreatorService getPolishNotationCreatorService() {
        return polishNotationCreatorService;
    }

    public PolishNotationCalculatorService getPolishNotationCalculatorService() {
        return polishNotationCalculatorService;
    }

    public ProxyExpressionsInTextService getProxyExpressionsInTextService() {
        return proxyExpressionsInTextService;
    }
}
