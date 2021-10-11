package by.training.xml.service;

import by.training.xml.entity.Gem;
import by.training.xml.service.impl.GemXmlServiceImpl;

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

    private final XmlService<Gem> gemXmlService;

    private ServiceFactory() {
        gemXmlService = new GemXmlServiceImpl();
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

    public XmlService<Gem> getGemXmlService() {
        return gemXmlService;
    }
}
