package dev.omedia.creational.singleton;

public enum SingletonEnumVariant {
    INSTANCE;

    private String host;

    SingletonEnumVariant() {
        this.init();
    }

    private void init() {
        this.host = "8.8.8.8";
    }

    public String getHost() {
        return this.host;
    }
}
