package ink.reactor.server.plugin.listener;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.tinylog.Logger;

import ink.reactor.api.plugin.Plugin;
import ink.reactor.api.plugin.listener.EventExecutor;
import ink.reactor.api.plugin.listener.Listener;
import ink.reactor.server.plugin.listener.executor.ListenerReflectionExecutor;

public final class ListenerLoader {

    public static List<Class<?>> getEventTypes(final Object object) {
        final Method[] methods = object.getClass().getDeclaredMethods();
        if (methods.length == 0) {
            return null;
        }

        final List<Class<?>> classes = new ArrayList<>(methods.length);
        for (final Method method : methods) {
            if (method.getParameterCount() == 1 && method.getDeclaredAnnotation(Listener.class) != null) {
                classes.add(method.getParameterTypes()[0]);
            }
        }
        return classes;
    }

    public static List<RegisteredListener> load(final Object object, final Plugin plugin, EventExecutor executor) {
        final Class<?> sourceClass = object.getClass();
        final Method[] methods = sourceClass.getDeclaredMethods();
        if (methods.length == 0) {
            Logger.info("The class {} don't contains any method", object);
            return List.of();
        }

        final List<RegisteredListener> listeners = new LinkedList<>();
    
        for (final Method method : methods) {
            final Listener listener = method.getAnnotation(Listener.class);
            if (listener == null) {
                continue;
            }

            if (method.getParameterCount() != 1) {
                Logger.warn("Error trying to load the listener {} in the class {}. The method need be exactly 1 parameter", method.getName(), object);
                continue;
            }

            final Class<?> firstParameter = method.getParameterTypes()[0];
            if (executor == null) {
                final MethodHandle methodHandle;
                try {
                    methodHandle = MethodHandles.publicLookup().unreflect(method);
                } catch (final IllegalAccessException e) {
                    Logger.error("Error trying to load the listener {} in the class {}", method.getName(), object.getClass());
                    Logger.error(e);
                    continue;
                }
                executor = new ListenerReflectionExecutor(object, listener, methodHandle);
            }

            listeners.add(new RegisteredListener(sourceClass, plugin, listener, executor, firstParameter));
        }

        return listeners;
    }
}