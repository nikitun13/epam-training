package by.training.information.service;

import by.training.information.entity.TextComponent;

/**
 * Describes interface of a service that
 * proxies expressions in the text for
 * calculating them during {@link TextComponent#collect()}.
 *
 * @author Nikita Romanov
 * @see TextComponent
 */
public interface ProxyExpressionsInTextService {

    /**
     * Proxies all expressions in the {@code component}
     * for calculating them during {@link TextComponent#collect()}..
     *
     * @param component text component for proxying expressions.
     * @return new {@code TextComponent} with proxied expressions.
     */
    TextComponent proxyExpressions(TextComponent component);
}
