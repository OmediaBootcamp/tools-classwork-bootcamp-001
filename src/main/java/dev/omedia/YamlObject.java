package dev.omedia;

import java.util.HashMap;
import java.util.Map;


public class YamlObject {
    private Map<String, Container> services;


    public YamlObject() {
        services = new HashMap<>();
    }

    public void add(String name, Container container) {
        services.put(name, container);
    }

    public Map<String, Container> getServices() {
        return services;
    }

    public void setServices(Map<String, Container> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "YamlObject{" + "services=" + services + '}';
    }
}
