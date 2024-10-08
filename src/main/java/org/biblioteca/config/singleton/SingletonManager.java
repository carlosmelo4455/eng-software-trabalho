package org.biblioteca.config.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SingletonManager {
    private static final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    private SingletonManager() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz) {
        return (T) instances.computeIfAbsent(clazz, k -> {
            try {
                if (clazz.isAnnotationPresent(Singleton.class)) {
                    var constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                } else {
                    throw new RuntimeException("Classe " + clazz.getName() + " não está anotada com @Singleton");
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro criando instancia singleton para " + clazz.getName(), e);
            }
        });
    }
}