package by.training.information.service.impl;

import by.training.information.entity.ReversePolishNotation;
import by.training.information.entity.TextComponent;
import by.training.information.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyExpressionsInTextServiceImpl
        implements ProxyExpressionsInTextService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(ProxyExpressionsInTextServiceImpl.class);

    @Override
    public TextComponent proxyExpressions(final TextComponent component) {
        log.debug("received component: {}", component);
        return switch (component.getType()) {
            case EXPRESSION -> createProxy(component);
            case WORD, SYMBOL -> component;
            default -> findInside(component);
        };
    }

    private TextComponent createProxy(final TextComponent component) {
        log.debug("creating proxy for: {}", component);
        return (TextComponent) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{TextComponent.class},
                new ExpressionInvocationHandler(component)
        );
    }

    private TextComponent findInside(final TextComponent component) {
        log.debug("received component for finding inside: {}", component);
        List<TextComponent> childrenComponents
                = component.getChildrenComponents();
        for (int i = 0; i < childrenComponents.size(); ++i) {
            TextComponent original = childrenComponents.get(i);
            TextComponent maybeProxy = proxyExpressions(original);
            if (original != maybeProxy) {
                log.debug("replacing original with proxy");
                childrenComponents.set(i, maybeProxy);
            }
        }
        return component;
    }

    private record ExpressionInvocationHandler(TextComponent original)
            implements InvocationHandler {

        private static final ReversePolishNotationCreatorService POLISH_NOTATION_CREATOR_SERVICE;
        private static final PolishNotationCalculatorService POLISH_NOTATION_CALCULATOR_SERVICE;

        static {
            ServiceFactory factory = ServiceFactory.getInstance();
            POLISH_NOTATION_CREATOR_SERVICE
                    = factory.getPolishNotationCreatorService();
            POLISH_NOTATION_CALCULATOR_SERVICE
                    = factory.getPolishNotationCalculatorService();
        }

        @Override
        public Object invoke(final Object proxy,
                             final Method method,
                             final Object[] args) {
            Object result = null;
            try {
                result = method.invoke(original, args);
                if (method.getName().equals("collect")) {
                    log.debug("collect method in proxy was invoked");
                    ReversePolishNotation notation = POLISH_NOTATION_CREATOR_SERVICE
                            .createNotation((String) result);
                    int newResult = POLISH_NOTATION_CALCULATOR_SERVICE
                            .calculateExpression(notation);
                    log.debug("replacing {} with {}", result, newResult);
                    result = String.valueOf(newResult);
                }
            } catch (IllegalAccessException
                    | InvocationTargetException
                    | ServiceException e) {
                log.error(e);
            }
            return result;
        }
    }
}
