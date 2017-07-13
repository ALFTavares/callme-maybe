package org.academiadecodigo.hackaton.client.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by André Martins <Code Cadet>
 *         Project LoginMVC (29/06/17)
 *         <Academia de Código_>
 */
public class ServiceLocator {

    private static ServiceLocator instance = null;

    private final Map<String, Service> services;

    private ServiceLocator() {

        services = new HashMap<>();

    }

    public static ServiceLocator getInstance() {

        if (instance == null) {

            synchronized (ServiceLocator.class) {

                if (instance == null) {

                    instance = new ServiceLocator();

                }

            }

        }

        return instance;

    }

    public void add(Service service) {

        services.put(service.getName(), service);

    }

    public void remove(Class<? extends Service> type) {

        services.remove(type.getSimpleName());

    }

    public <T extends Service> T get(Class<T> type) {

        Service service = services.get(type.getSimpleName());

        return service == null ? null : type.cast(service);

    }

}
