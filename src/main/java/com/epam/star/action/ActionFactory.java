package com.epam.star.action;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActionFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(ActionFactory.class);

    static Map<String, Action> actionMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections(ActionFactory.class.getPackage().getName());
        Set<Class<?>> actions = reflections.getTypesAnnotatedWith(MappedAction.class);

        for (Class<?> actionClass : actions) {
            MappedAction mappedAction = actionClass.getAnnotation(MappedAction.class);

            Action action = null;
            try {
                action = (Action) actionClass.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error(e.toString());
            } catch (IllegalAccessException e) {
                LOGGER.error(e.toString());
            }
            actionMap.put(mappedAction.value(), action);
        }
    }

    public static Action getAction(String actionName) {
        return actionMap.get(actionName);
    }
}
