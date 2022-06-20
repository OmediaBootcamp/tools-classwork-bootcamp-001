package dev.omedia.creational.prototype;

public interface Prototype<T extends Prototype> {
    T clone();
}
