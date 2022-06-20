package dev.omedia.creational.singleton;

import java.util.Objects;

public final class Singleton {
    private final String host;
    private static Singleton instance;

    private Singleton() {
        this.host = "12.212.93.189";
    }

    public static Singleton getInstance() {
        return Objects.requireNonNullElseGet(instance, () -> instance = new Singleton());
    }

    public String getHost() {
        return this.host;
    }

}
